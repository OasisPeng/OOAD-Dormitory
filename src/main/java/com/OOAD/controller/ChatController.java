package com.OOAD.controller;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.OOAD.domain.Chat;
import com.OOAD.domain.User;
import com.OOAD.service.IChatService;
import com.OOAD.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private IChatService chatService;
    @Autowired
    private IUserService userService;

    @GetMapping("/listSingle")
    public Result listSingle(@RequestParam String fromUser, String toUser) {
        Result result = new Result();
        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chat::getFromuser, fromUser);//设置查询条件
        lambdaQueryWrapper.eq(Chat::getTouser, toUser);
        lambdaQueryWrapper.eq(Chat::getIsGroup, 0);
        List<Chat> list1 = chatService.list(lambdaQueryWrapper);

        LambdaQueryWrapper<Chat> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper2.eq(Chat::getFromuser, toUser);//设置查询条件
        lambdaQueryWrapper2.eq(Chat::getTouser, fromUser);
        lambdaQueryWrapper2.eq(Chat::getIsGroup, 0);
        List<Chat> list2 = chatService.list(lambdaQueryWrapper2);
        // 合并两个列表
        List<Chat> mergedList = Stream.concat(list1.stream(), list2.stream()).sorted(Comparator.comparing(Chat::getTime)).collect(Collectors.toList());
        // 按照 Chat 对象的时间字段排序

        result.setCode(2010);
        result.setMsg("成功");
        result.setData(mergedList);
        return result;
    }

    @GetMapping("/listGroup")
    public Result listGroup(@RequestParam String toUser) {
        Result result = new Result();
        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chat::getTouser, toUser);
        lambdaQueryWrapper.eq(Chat::getIsGroup, 1);
        List<Chat> list = chatService.list(lambdaQueryWrapper);
        result.setCode(2010);
        result.setMsg("成功");
        result.setData(list);
        return result;
    }

    @GetMapping("/getUserList")
    public Result getUserList(@RequestParam String fromUser) {
        Result result = new Result();
        Set<User> users = new HashSet<>();

        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chat::getIsGroup, 0);
        lambdaQueryWrapper.eq(Chat::getFromuser, fromUser);
        List<Chat> list = chatService.list(lambdaQueryWrapper);
        for (Chat chat : list) {
            User user = userService.getById(Integer.parseInt(chat.getTouser()));
            users.add(user);
        }

        LambdaQueryWrapper<Chat> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(Chat::getIsGroup, 0);
        lambdaQueryWrapper1.eq(Chat::getTouser, fromUser);
        List<Chat> list1 = chatService.list(lambdaQueryWrapper1);
        for (Chat chat : list1) {
            User user = userService.getById(Integer.parseInt(chat.getFromuser()));
            users.add(user);
        }
        result.setCode(2010);
        result.setMsg("成功");
        result.setData(users.stream().toList());
        return result;
    }

    @GetMapping("/getGroupList")
    public Result getGroupList(@RequestParam String fromUser) {
        Result result = new Result();
        Set<String> groups = new HashSet<>();

        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chat::getIsGroup, 1);
        List<Chat> list = chatService.list(lambdaQueryWrapper);
        for (Chat chat : list) {
            String str = chat.getTouser();
            // 使用正则表达式匹配最后一个括号内的内容
            Pattern pattern = Pattern.compile("\\(([^)]+)\\)$");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                String contentInsideLastParentheses = matcher.group(1);
                String[] array = contentInsideLastParentheses.split(",");
                if (Arrays.asList(array).contains(fromUser)) {
                    groups.add(str);
                }
            }
        }
        result.setCode(2010);
        result.setMsg("成功");
        result.setData(groups.stream().toList());
        return result;
    }

    @GetMapping("/unReadNums")
    public Result unReadNums(@RequestParam String toUser, @RequestParam String currentUser) {
        Result result = new Result();
        Dictionary<String, Integer> dict = new Hashtable<>();
        //私聊未读
        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chat::getReaded, 0);
        lambdaQueryWrapper.eq(Chat::getIsGroup, 0);
        lambdaQueryWrapper.eq(Chat::getTouser, toUser);
        List<Chat> list = chatService.list(lambdaQueryWrapper);

        Map<String, List<Chat>> collect = list.stream().collect(Collectors.groupingBy(Chat::getFromuser));
        collect.forEach((k, v) -> dict.put(k, v.size()));
        dict.put(currentUser, 0);

//        //群聊未读
//        LambdaQueryWrapper<Chat> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper1.eq(Chat::getReaded, 0);
//        lambdaQueryWrapper1.eq(Chat::getIsGroup, 1);
//        List<Chat> list1 = chatService.list(lambdaQueryWrapper1);
//        List<Chat> list2 = new ArrayList<>();
//        for (Chat chat : list1) {
//            String str = chat.getTouser();
//            // 使用正则表达式匹配最后一个括号内的内容
//            Pattern pattern = Pattern.compile("\\(([^)]+)\\)$");
//            Matcher matcher = pattern.matcher(str);
//            if (matcher.find()) {
//                String contentInsideLastParentheses = matcher.group(1);
//                String[] array = contentInsideLastParentheses.split(",");
//                if (Arrays.asList(array).contains(toUser)) {
//                    list2.add(chat);
//                }
//            }
//        }
//        Map<String, List<Chat>> collect1 = list2.stream().collect(Collectors.groupingBy(Chat::getTouser)); //按群聊名字分组
//        collect1.forEach((k, v) -> dict.put(k, v.size()));

        result.setCode(2010);
        result.setMsg("成功");
        result.setData(dict);
        return result;
    }

    @GetMapping("/setSingleReaded")
    public Result setSingleReaded(@RequestParam String fromUser, @RequestParam String toUser) {
        Result result = new Result();
        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chat::getReaded, 0);
        List<Chat> list = new ArrayList<>();
        lambdaQueryWrapper.eq(Chat::getFromuser, toUser);
        lambdaQueryWrapper.eq(Chat::getTouser, fromUser);
        list = chatService.list(lambdaQueryWrapper);

        if (!list.isEmpty()) {
            // 将 list 中的 Chat 记录的 readed 属性更新为 1
            LambdaUpdateWrapper<Chat> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(Chat::getReaded, 1)
                    .in(Chat::getId, list.stream().map(Chat::getId).collect(Collectors.toList()));
            chatService.update(updateWrapper);

            result.setCode(2010);
            result.setMsg("更新成功");
        } else {
            result.setCode(2011);
            result.setMsg("未找到符合条件的记录");
        }
        return result;
    }

    @PostMapping("/sendMessage")
    public Result sendMessage(@RequestBody Chat message) {
        Result result = new Result();

        // 保存消息到数据库
        boolean saved = chatService.save(message);

        if (saved) {
            result.setCode(2010);
            result.setMsg("消息发送成功");
        } else {
            result.setCode(2011);
            result.setMsg("消息发送失败");
        }

        return result;
    }

}
