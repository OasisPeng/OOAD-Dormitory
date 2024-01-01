<script>
export default {
    name:"DormitoryPropose",
    methods:{
        loadDateData() {
            // 发起后端请求以加载日期数据
            this.$axios
                .get(this.$httpUrl+'/openTime/'+ this.studentType,{
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}) // 请将路径替换为您的实际后端接口路径
                .then( (res => {
                    const data = res.data.data; // 假设后端返回的数据包含日期信息
                    if (res.data.code === 2010) {
                        // 解析日期并设置到组件的数据属性
                        this.grabStartDate = new Date(data.openTime);
                        this.grabEndDate = new Date(data.closeTime);
                        this.submitEndDate = this.grabStartDate;
                        // 设置submitStartDate为submitEndDate往前一个月
                        this.submitStartDate = new Date(this.submitEndDate);
                        this.submitStartDate.setMonth(this.submitStartDate.getMonth() - 1);
                        // 获取当前日期
                        const currentDate = new Date();

                        // 根据日期比较来设置active的值
                        if (currentDate >= this.submitStartDate && currentDate <= this.submitEndDate) {
                            this.active = 0; // 在提交宿舍日期范围内
                        } else if (currentDate >= this.grabStartDate && currentDate <= this.grabEndDate && this.successDorm === '') {
                            this.active = 1; // 在抢宿舍日期范围内
                        } else {
                            this.active = 2; // 其他情况
                            console.log(1234)
                            console.log((this.successDorm))
                        }
                    }else {
                        // alert('获取数据失败')
                    }
                }))
                .catch((error) => {
                    console.error('Failed to load date data:', error);
                });
        },
        getTeam(){
            let team=[]
            this.$axios.get(this.$httpUrl+'/team/'+this.teamId, {
                withCredentials: true,
                headers:{
                    'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                }}
            ).then(res=>{
                if (res.data.code===2010) {
                    if(res.data.data.headId === this.user.id){
                        team.push(res.data.data)
                        this.optionsTeam = team.map(item => ({ value: item, label: item.name }));
                        console.log('team')
                        console.log(this.optionsTeam)
                    }
                } else {
                    console.log(res.data.msg)
                    // 登录失败，可以显示错误消息
                }
            })
        },
        //选择宿舍
        fetchAreaOptions() {
            this.areaOptions = ['二期','湖畔','荔园','欣园'].map(item => ({ value: item, label: item }));
        },
        fetchBuildingOptions(area) {
            this.$axios.get(this.$httpUrl+'/dorm2/buildings/'+area, {
                withCredentials: true,
                headers:{
                    'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.buildingOptions = res.data.data.map(item => ({ value: item, label: item }));
                } else {
                    console.log(res.data.msg)
                    // 登录失败，可以显示错误消息
                }
            })
        },
        fetchRoomOptions(building) {
            this.$axios.get(this.$httpUrl+'/dorm2/'+this.selectedArea+'/'+building, {
                withCredentials: true,
                headers:{
                    'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.roomOptions = res.data.data.map(item => ({ value: item, label: item }));
                } else {
                    console.log(res.data.msg)
                    // 登录失败，可以显示错误消息
                }
            })
        },
        getPreferredDormitory(){

        },
        submit(){
            // 判断所选信息是否为空
            if (
                this.selectedTeam === "" ||
                this.selectedArea === "" ||
                this.selectedBuilding === "" ||
                this.selectedRoom === ""
            ) {
                this.$message({
                    type: "warning",
                    message: "请完善组队和寝室信息",
                });
                return; // 中断操作，不继续执行下面的逻辑
            }

            //退出确认
            this.$confirm('您确定要所选组队和寝室信息吗？','提示',{
                confirmButtonText:'确定',
                type:'warning',
                center:true
            }).then(()=>{
                this.submittedDormitory = this.selectedArea+'-'+this.selectedBuilding+'-'+this.selectedRoom;
                this.submittedTeam = this.selectedTeam;
                console.log(this.submittedDormitory)
                console.log(this.submittedTeam.id)
                this.$axios.get(this.$httpUrl+'/team/submit?teamId='+this.submittedTeam.id+'&dormName='+this.submittedDormitory,
                    {
                        withCredentials: true,
                        headers:{
                            'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                        }}
                ).then(res=>{
                    if (res.data.code===2020) {
                        this.$message({
                            type:'success',
                            message:'提交成功'
                        })
                    } else {
                        console.log(res.data.msg)
                        // 登录失败，可以显示错误消息
                    }

                })
                // this.$router.push('/');//路由
                // sessionStorage.clear()
            })
                .catch(()=>{
                    this.$message({
                        type:'info',
                        message:'已取消提交'
                    })
                })
        },
        grab(){
            // 抢、
            // 抢到了就更新team的dorm,user的dorm_id和
            this.$axios.post(this.$httpUrl + '/dorm/qiang/'+this.submittedTeam.id+'/'+this.submittedDormId,null,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if(res && res.data.code === 2040 ){
                    this.$message({
                        type: 'success',
                        message: '已抢到该宿舍'
                    })
                    this.successDorm = this.submittedDormitory
                    this.active = 2
                }else {
                    this.$message({
                        type: 'info',
                        message: '抢宿舍失败，请重试或更换其他宿舍'
                    })
                }
            })


        },
        formatDateRange(startDate, endDate) {
            // 格式化开始日期
            const formattedStartDate = this.formatDate(startDate);
            // 格式化结束日期
            const formattedEndDate = this.formatDate(endDate);

            return `${formattedStartDate} - ${formattedEndDate}`;
        },
        formatDate(date) {
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要加1
            const day = String(date.getDate()).padStart(2, '0');
            const hours = String(date.getHours()).padStart(2, '0');
            const minutes = String(date.getMinutes()).padStart(2, '0');

            return `${year}/${month}/${day} ${hours}:${minutes}`;
        },
        handleAreaChange() {
            // 当选择的区域变化时，更新楼栋列表和房间号列表
            this.selectedBuilding = "";
            this.selectedRoom = "";
            this.buildingOptions = [];
            this.roomOptions = [];
            if (this.selectedArea) {
                this.fetchBuildingOptions(this.selectedArea);
            }
        },
        handleBuildingChange() {
            // 当选择的楼栋变化时，更新房间号列表
            this.selectedRoom = "";
            this.roomOptions = [];
            if (this.selectedBuilding) {
                this.fetchRoomOptions(this.selectedBuilding);
            }
        },
        loadSubmittedData() { //所在组队是否有提交宿舍
            console.log(this.teamId)
            this.$axios.get(this.$httpUrl+'/team/'+this.teamId,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    const team = res.data.data
                    if (team.submittedDorm == null || team.submittedDorm === ''){
                        this.submittedTeam = '';
                        this.submittedDormitory = '';
                    }else {
                        this.submittedTeam = team;
                        this.submittedDormitory = team.submittedDorm;
                        this.successDorm = JSON.parse(sessionStorage.getItem('UserData')).dormId === null? '':this.submittedDormitory
                        if (this.successDorm !== ''){
                            this.active = 2  // 如果已经有抢到的宿舍，直接跳转到第三个页面
                        }
                        this.getTeamHeadName();
                        this.getTeamMembers()
                    }
                } else {
                    console.log(res.data.msg)
                }

            })
        },
        getFavouriteNum() {

        },
        getAllFavoriteDorms(){
            this.$axios.get(this.$httpUrl+'/favouriteDorm/user?id='+this.user.id,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.favoriteDorms = res.data.data
                    console.log(this.favoriteDorms)
                } else {
                    console.log(res.data.msg)
                }

            })
        },
        getTeamHeadName() {  //通过队长id(submittedTeam.headId)获取队长name
            this.$axios.get(this.$httpUrl+'/user/'+this.submittedTeam.headId,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    this.teamHeadName = res.data.data.name
                } else {
                    console.log(res.data.msg)
                }

            })
        },
        getTeamMembers() {  //通过队伍id获得成员信息
            this.$axios.get(this.$httpUrl+'/users/'+this.submittedTeam.id,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2010) {
                    // 过滤掉队长
                    this.teamMembers = res.data.data.filter(member => member.id !== this.submittedTeam.headId);
                } else {
                    console.log(res.data.msg)
                }

            })
        },
        resubmit() {
            if (
                this.selectedArea === "" ||
                this.selectedBuilding === "" ||
                this.selectedRoom === ""
            ) {
                this.$message({
                    type: "warning",
                    message: "请完善寝室信息",
                });
                return; // 中断操作，不继续执行下面的逻辑
            }
            this.submittedDormitory = this.selectedArea+'-'+this.selectedBuilding+'-'+this.selectedRoom;
            this.$axios.get(this.$httpUrl+'/team/submit?teamId='+this.submittedTeam.id+'&dormName='+this.submittedDormitory,
                {
                    withCredentials: true,
                    headers:{
                        'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                    }}
            ).then(res=>{
                if (res.data.code===2020) {
                    this.$message({
                        type:'success',
                        message:'提交成功'
                    })
                } else {
                    console.log(res.data.msg)
                    // 登录失败，可以显示错误消息
                }

            })
            this.dialogVisible = false;
        },
        getDescription() {
            if (this.successDorm === this.submittedDormitory && this.successDorm !== '') {
                return `你已经成功抢到${this.successDorm}宿舍，欢迎尽快和室友提包入住`;
            } else {
                // 根据其他条件返回不同的描述
                // 如果申请没有结束，可以返回其他描述
                return "寝室申请已经结束";
            }
        }
    },
    created() {
        // 判断用户的学生类型并设置对应的值
        if (this.user.studentType === '研究生' && this.user.sex === '男') {
            this.studentType = 1;
        } else if (this.user.studentType === '研究生' && this.user.sex === '女') {
            this.studentType = 2;
        } else if (this.user.studentType === '博士生' && this.user.sex === '男') {
            this.studentType = 3;
        } else if(this.user.studentType === '博士生' && this.user.sex === '女') {
            this.studentType = 4
        }
        this.loadDateData(); // 加载日期数据
        this.loadSubmittedData()
        this.fetchAreaOptions();
        this.getTeam();
        this.getAllFavoriteDorms()
    },
    data() {
        return {
            submittedTeam:"",
            submittedDormitory:"",
            submittedDormId:'',
            //第二个页面：已提交信息
            teamMembers: [], // 队伍成员列表
            teamHeadName: '', //队长名字

            user: JSON.parse(sessionStorage.getItem('UserData')),
            teamId: JSON.parse(sessionStorage.getItem('UserData')).teamId,
            studentType: 0,
            optionsTeam:[],
            options:[],
            selectedTeam:"",
            selectedDormitory:"",
            centerDialogVisible: false,
            active: 2,
            activeName:'1',
            submitStartDate: new Date('2000/00/00 00:00:00'), // 提交宿舍开始日期
            submitEndDate: new Date('2000/00/00 00:00:00'), // 提交宿舍结束日期
            grabStartDate: new Date('2000/00/00 00:00:00'), // 抢宿舍开始日期
            grabEndDate: new Date('2000/00/00 00:00:00'), // 抢宿舍结束日期
            //选择寝室
            selectedArea: '',
            selectedBuilding: '',
            selectedRoom: '',
            areaOptions: [],
            buildingOptions: [],
            roomOptions: [],
            successDorm: '',
            dialogVisible: false,
            //收藏的寝室
            favoriteDorms: [] ,
            // favoriteNums: '', //多少人收藏
        }
    },
    watch: {
        selectedDormitory(newVal) {
            // 监听 selectedDormitory 变化，拆分成三个变量
            if (newVal) {
                this.selectedArea = newVal.distribution;
                this.selectedBuilding = newVal.building;
                this.selectedRoom = newVal.room;
            }

        },
        submittedDormitory(newVal) {
            if(newVal) {
                // 假设 submittedDormitory 的值为 '湖畔-3栋-2楼-222'
                const parts = newVal.split('-');

                // 获取分割后的值
                this.selectedArea = parts[0]; // '湖畔'
                this.selectedBuilding = parts[1]; // '3栋'
                this.selectedRoom = parts[2]; // '2楼-222'

                // 更新所选房间id
                this.$axios.get(this.$httpUrl + '/dorm/getDormId?dis=' + this.selectedArea + '&building=' + this.selectedBuilding + '&room=' + this.selectedRoom,
                    {
                        withCredentials: true,
                        headers: {
                            'Authorization': "Bearer" + " " + JSON.parse(sessionStorage.getItem('CurUser')).token
                        }
                    }
                ).then(res => {
                    if (res.data.code === 2010) {
                        this.submittedDormId = res.data.data
                        console.log(res.data)
                    } else {
                        console.log(res.data.msg)
                    }

                })
            }
        }
    },
    computed: {
        // 提交宿舍步骤的描述
        submitTitle() {
            return `提交宿舍 (${this.formatDateRange(this.submitStartDate, this.submitEndDate)})`;
        },
        // 抢宿舍步骤的描述
        grabTitle() {
            return `抢宿舍 (${this.formatDateRange(this.grabStartDate, this.grabEndDate)})`;
        },
    },
}
</script>

<template>
    <div>
        <div style="margin: 10px 30px 10px 150px; font-family: arial, sans-serif">
            <el-steps :active="active" finish-status="success" >
                <el-step :title="submitTitle" description="由队长提交心仪的宿舍，可以从收藏中选择，组队信息在抢宿舍阶段不可变更"></el-step>
                <el-step :title="grabTitle" description="手速拼起来~"></el-step>
            </el-steps>
        </div>
        <el-divider></el-divider>
        <div>
            <div v-if="active === 0">
                <div style="display: flex; flex-direction: column; justify-content:center; align-items: flex-start; height: 500px; margin-left: 300px; font-family: arial, sans-serif;font-size: 15px">
                    <el-card class="box-card">
                        <div style="font-size: 7px; color: crimson">
                            <span>
                                您已提交 {{ submittedDormitory }} 寝室，组队名称为 {{ submittedTeam.name }}，时间截止前可随时更改组队和寝室信息
                            </span>
                        </div>
                        <div style="margin-top: 30px">
                            您是：
                            <el-radio-group v-model="studentType">
                                <!-- 如果 studentType 的初始值为 0，则选择 "本科生" 并禁用 "研究生" -->
                                <!-- 如果 studentType 的初始值为 1，则选择 "研究生" 并禁用 "本科生" -->
                                <el-radio :label="1"  :disabled="studentType === 3||studentType === 4">研究生</el-radio>
                                <el-radio :label="3"  :disabled="studentType === 1||studentType === 2">博士生</el-radio>
                            </el-radio-group>
                        </div>
                        <div style="margin-top: 23px">
                            选择您的组队：
                            <el-select v-model="selectedTeam" clearable placeholder="请选择">
                                <el-option
                                        v-for="item in optionsTeam"
                                        :key="item.label"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                            <span style="font-size: 10px; margin-left: 5px">(仅队长可选)</span>
                        </div>
                        <div style="margin-top: 23px">
                            选择寝室：
                            <!-- 区域选择 -->
                            <el-select v-model="selectedArea" placeholder="请选择区域" @change="handleAreaChange" style="width: 120px">
                                <el-option v-for="area in areaOptions" :key="area.value" :label="area.label" :value="area.value"></el-option>
                            </el-select>
                            -
                            <!-- 楼栋选择 -->
                            <el-select v-model="selectedBuilding" placeholder="请选择楼栋" @change="handleBuildingChange" :disabled="!selectedArea" style="width: 120px">
                                <el-option v-for="building in buildingOptions" :key="building.value" :label="building.label" :value="building.value"></el-option>
                            </el-select>
                            -
                            <!-- 房间号选择 -->
                            <el-select v-model="selectedRoom" placeholder="请选择房间号" :disabled="!selectedBuilding" style="width: 120px">
                                <el-option v-for="room in roomOptions" :key="room.value" :label="room.label" :value="room.value"></el-option>
                            </el-select>

                            <el-button type="text" class="button" style="margin-left: 7px" @click="centerDialogVisible = true">从收藏中选择</el-button>
                        </div>
                        <div style="margin-top: 20px">
                            <el-button slot="reference" type="primary" @click="submit" round>提交</el-button>
                            <el-dialog
                                    title="我的收藏"
                                    :visible.sync="centerDialogVisible"
                                    width="30%"
                                    center
                                    style="margin-left: 210px">
                                <el-radio-group v-model="selectedDormitory">
                                    <div v-for="dorm in favoriteDorms" :key="dorm.id">
                                        <!-- 使用插值语法动态设置label -->
                                        <el-radio :label="dorm">
                                            {{ dorm.distribution + '-' + dorm.building + '-' + dorm.room }}
                                        </el-radio>
                                    </div>
                                </el-radio-group>

                                <span slot="footer" class="dialog-footer">
                                    <el-button @click="centerDialogVisible = false">取 消</el-button>
                                    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
                                </span>
                            </el-dialog>
                        </div>
                    </el-card>
                </div>
            </div>

            <div v-if="active=== 1">
                <div style="display: flex; flex-direction: column; justify-content:center; align-items: flex-start; height: 500px; margin-left: 310px; font-family: arial, sans-serif;font-size: 15px">
                    <el-card class="box-card">
                        <div style="margin-top: 20px">
                            <el-row>
                                <span>您的组队信息：</span>
                                <div style="margin-top: 10px;">
                                    <el-collapse v-model="activeName" accordion>
                                        <el-collapse-item name="1">
                                            <template slot="title">
                                                <div style="color: #817f7f">队名：{{ submittedTeam.name }}</div>
                                            </template>
                                            <div style="color: #817f7f">队长：{{ submittedTeam.headId }} {{ teamHeadName }}</div>
                                            <div style="color: #817f7f">成员：
                                                {{ teamMembers.map(member => `${member.id} ${member.name}`).join(', ') }}
                                            </div>

                                        </el-collapse-item>
                                    </el-collapse>
                                </div>
                            </el-row>
                        </div>
                        <div style="margin-top: 10px;display: flex;align-items: flex-start;">
                            <span style="margin-right: 10px;">您的所选寝室：</span>
                            <div>
                                <el-card :body-style="{ padding: '0px' }" style="height: 200px; width: 180px">
                                    <img src="../../assets/img.jpg" class="image" style="height: 160px;">
                                    <div style="padding: 8px;">
                                        <span>{{ submittedDormitory }}</span>>
                                    </div>
                                </el-card>
                                <div>
                                    <!-- 点击按钮触发对话框显示 -->
                                    <el-button type="text" class="button" style="margin-left: 7px" @click="dialogVisible = true">更换其他可选寝室</el-button>

                                    <!-- 对话框组件 -->
                                    <el-dialog :visible.sync="dialogVisible" title="更换寝室" width="32%" @close="dialogVisible = false">
                                        <!-- 对话框内容 -->
                                        <!-- 这里可以放入更换寝室的相关内容 -->
                                        <!-- 区域选择 -->
                                        <el-select v-model="selectedArea" placeholder="请选择区域" @change="handleAreaChange" style="width: 120px">
                                            <el-option v-for="area in areaOptions" :key="area.value" :label="area.label" :value="area.value"></el-option>
                                        </el-select>
                                        -
                                        <!-- 楼栋选择 -->
                                        <el-select v-model="selectedBuilding" placeholder="请选择楼栋" @change="handleBuildingChange" :disabled="!selectedArea" style="width: 120px">
                                            <el-option v-for="building in buildingOptions" :key="building.value" :label="building.label" :value="building.value"></el-option>
                                        </el-select>
                                        -
                                        <!-- 房间号选择 -->
                                        <el-select v-model="selectedRoom" placeholder="请选择房间号" :disabled="!selectedBuilding" style="width: 125px">
                                            <el-option v-for="room in roomOptions" :key="room.value" :label="room.label" :value="room.value"></el-option>
                                        </el-select>
                                        <!-- 对话框底部按钮 -->
                                        <div style="margin-top: 20px">
                                            <span class="dialog-footer">
                                                <el-button @click="dialogVisible = false">取消</el-button>
                                                <el-button type="primary" @click="resubmit">确定</el-button>
                                            </span>
                                        </div>
                                    </el-dialog>
<!--                                    <el-button type="text" class="button" style="margin-left: 7px" @click="">选择候补寝室</el-button>-->
                                </div>

                            </div>
                        </div>
                        <div style="margin-top: 20px; margin-left: 270px">
                            <el-button slot="reference" type="success" style="font-size: 17px" @click="grab">抢</el-button>
                        </div>
                    </el-card>
                </div>

            </div>

            <div v-if="active=== 2">
                <!--                <span>寝室申请已经结束</span>-->
                <el-empty :description="getDescription()"></el-empty>
            </div>
        </div>
    </div>
</template>


<style scoped>

.box-card {
    width: 645px;
//height: 500px;
}
</style>