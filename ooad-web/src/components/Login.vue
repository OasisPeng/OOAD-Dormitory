

<template>



  <div class="body">

    <div class="main">
      <div class="title-container">
        <h2  class="conference-title" >Sustech 研究生宿舍选择系统</h2>
      </div>
      <div class="buttons3">
        <div v-if="button1Visible" @click="showLoginDialog" class="enterBut">
          <span>登入</span>
        </div>

        <div v-if="button1Visible" @click="showRegisterDialog" class="enterBut" style="margin-left: 10px;">
          <span>修改密码</span>
        </div>
        <div v-if="button1Visible" @click="showAdminDialog" class="enterBut" style="margin-left: 10px;">
          <span>管理员登录</span>
        </div>
      </div>

<!--      <div class="buttons">-->
<!--        <button @click="showLoginDialog">登录</button>-->
<!--        <button @click="showRegisterDialog">注册</button>-->
<!--      </div>-->

      <div v-if="loginDialogVisible"  class="loginFrame">
        <p class="loginTitle">Login</p>
        <div  class="user">
          <label>
                <span>
                    <svg class="icon" height="20" p-id="3807" t="1586442913188"
                         version="1.1" viewBox="0 0 1024 1024" width="20" xmlns="http://www.w3.org/2000/svg"><path
                        d="M517.632 552.149333c-108.714667 0-197.162667-85.546667-197.162667-190.72 0-38.314667 11.690667-75.306667 33.877334-106.922666C391.04 202.026667 452.138667 170.666667 517.632 170.666667c65.408 0 126.464 31.274667 163.2 83.712 7.765333 11.093333 14.250667 22.869333 19.413333 35.072a21.333333 21.333333 0 1 1-39.338666 16.64 147.285333 147.285333 0 0 0-15.018667-27.221334C617.130667 237.824 569.173333 213.333333 517.632 213.333333c-51.626667 0-99.584 24.533333-128.426667 65.621334a143.445333 143.445333 0 0 0-26.069333 82.432c0 81.664 69.290667 148.096 154.453333 148.096 63.402667 0 119.722667-36.437333 143.36-92.8a21.333333 21.333333 0 0 1 39.338667 16.512c-30.378667 72.277333-102.016 118.954667-182.656 118.954666"
                        fill="#ffffff" p-id="3808"></path><path
                        d="M303.829333 627.456c-49.92 0-90.453333 41.088-90.453333 91.605333C213.333333 769.578667 253.866667 810.666667 303.786667 810.666667h416.341333C770.133333 810.666667 810.666667 769.578667 810.666667 719.061333c0-50.517333-40.533333-91.605333-90.453334-91.605333H303.786667zM720.213333 853.333333H303.829333C230.442667 853.333333 170.709333 793.088 170.709333 719.061333 170.666667 645.034667 230.4 584.789333 303.786667 584.789333h416.341333C793.6 584.789333 853.333333 645.034667 853.333333 719.061333 853.333333 793.088 793.6 853.333333 720.213333 853.333333z"
                        fill="#ffffff" p-id="3809"></path></svg>
                </span>
            <span>用户名</span>
            <input maxlength="16" placeholder="   UserName" type="text" v-model="loginUsername">
          </label>
        </div>
        <div class="password">
          <label>
                <span>
                    <svg class="icon" height="20" p-id="4677" t="1586443029761"
                         version="1.1" viewBox="0 0 1024 1024" width="20" xmlns="http://www.w3.org/2000/svg"><path
                        d="M511.176404 551.030949c-38.584889 0-69.974626 31.392323-69.974626 69.979798 0 15.341899 4.874343 29.909333 14.105859 42.121051 0.192646 0.261172 0.404687 0.513293 0.616727 0.768 17.287758 20.146424 18.545778 34.525091 18.620768 35.842586l0 39.965737c0 20.20202 16.434424 36.631273 36.631273 36.631273 20.20202 0 36.632566-16.434424 36.632566-36.631273l0-40.718222c0-0.457697-0.015515-0.919273-0.060768-1.373091 0-0.03103 0.090505-0.005172 0.095677-0.040081 0.100848-0.919273 0.408566-2.884525 1.343354-5.727677 1.883798-5.712162 6.30303-15.080727 16.707232-27.24202 0.151273-0.177131 0.297374-0.359434 0.439596-0.540444 9.702141-12.399192 14.828606-27.288566 14.828606-43.055838C581.161374 582.424566 549.767758 551.030949 511.176404 551.030949L511.176404 551.030949zM541.580929 644.313212c-24.46998 28.748283-25.667232 49.334303-25.434505 55.091717l0 40.308364c0 2.737131-2.232889 4.963556-4.97002 4.963556-2.737131 0-4.964848-2.226424-4.964848-4.963556L506.211556 699.552323c0-2.954343-0.81196-26.410667-25.903838-55.858424-4.873051-6.601697-7.444687-14.429091-7.444687-22.681859 0-21.126465 17.186909-38.319838 38.313374-38.319838 21.131636 0 38.323717 17.193374 38.323717 38.319838C549.500121 629.536323 546.76299 637.586101 541.580929 644.313212L541.580929 644.313212zM541.580929 644.313212"
                        fill="#ffffff" p-id="4678"></path><path
                        d="M777.580606 417.122263l-1.989818 0 0-0.232727-36.848485 0 0-101.520808c0-0.67103-0.045253-1.343354-0.126707-2.014384-6.923636-54.061253-33.298101-103.833859-74.272323-140.166465C623.009616 136.546263 569.792646 116.363636 514.505697 116.363636c-60.353939 0-117.095434 23.505455-159.773737 66.177293-42.67701 42.67701-66.176 99.419798-66.176 159.772444l0 74.78303-43.132121 0.025859c-29.812364 0-54.070303 24.256646-54.070303 54.070303l0 383.171232c0 29.818828 24.257939 54.071596 54.070303 54.071596l330.369293 0c0.479677 0 0.954182-0.020687 1.424808-0.065939l198.93398 0c0.469333 0.045253 0.943838 0.065939 1.428687 0.065939 29.808485 0 54.065131-24.252768 54.065131-54.071596L831.645737 471.192566C831.64703 441.378909 807.394263 417.122263 777.580606 417.122263L777.580606 417.122263zM320.246949 342.313374c0-51.889131 20.207192-100.671354 56.899232-137.358222 36.686869-36.69204 85.470384-56.899232 137.358222-56.899232 47.530667 0 93.282263 17.348525 128.806788 48.848162 34.990545 31.021253 57.597414 73.420283 63.733657 119.489939l0 100.495515-386.799192 0.232727L320.245657 342.313374 320.246949 342.313374zM799.954747 854.363798c0 11.773414-9.135838 21.449697-20.69204 22.313374l-0.126707 0L245.423838 876.739232c-12.338424 0-22.37802-10.03701-22.37802-22.375434L223.045818 471.192566c0-12.338424 10.039596-22.379313 22.37802-22.379313l58.984727-0.029737c0.808081 0 1.596768-0.081455 2.373818-0.196525 0.849455 0.135758 1.71701 0.231434 2.606545 0.231434l299.146343 0c0.288323 0.005172 0.731798 0.015515 1.313616 0.015515 1.247677 0 3.152162-0.029737 5.590626-0.156444l105.742222 0c0.656808 0.080162 1.322667 0.135758 2.000162 0.135758l54.398707 0c12.333253 0 22.374141 10.046061 22.374141 22.379313L799.954747 854.363798 799.954747 854.363798zM799.954747 854.363798"
                        fill="#ffffff" p-id="4679"></path></svg>
                </span>
            <span>密码</span>
            <input maxlength="16" placeholder="   Password" type="password" v-model="loginPassword">
          </label>
        </div>
        <div class="buttons2">
          <div @click="login" class="enterBut">
            <span>登入</span>
          </div>
          <div @click="out" class="enterBut" style="margin-left: 10px;">
            <span>退出</span>
          </div>
        </div>
      </div>


      <div v-if="admDialogVisible"  class="loginFrame">
        <p class="loginTitle">Login</p>
        <div  class="user">
          <label>
                <span>
                    <svg class="icon" height="20" p-id="3807" t="1586442913188"
                         version="1.1" viewBox="0 0 1024 1024" width="20" xmlns="http://www.w3.org/2000/svg"><path
                        d="M517.632 552.149333c-108.714667 0-197.162667-85.546667-197.162667-190.72 0-38.314667 11.690667-75.306667 33.877334-106.922666C391.04 202.026667 452.138667 170.666667 517.632 170.666667c65.408 0 126.464 31.274667 163.2 83.712 7.765333 11.093333 14.250667 22.869333 19.413333 35.072a21.333333 21.333333 0 1 1-39.338666 16.64 147.285333 147.285333 0 0 0-15.018667-27.221334C617.130667 237.824 569.173333 213.333333 517.632 213.333333c-51.626667 0-99.584 24.533333-128.426667 65.621334a143.445333 143.445333 0 0 0-26.069333 82.432c0 81.664 69.290667 148.096 154.453333 148.096 63.402667 0 119.722667-36.437333 143.36-92.8a21.333333 21.333333 0 0 1 39.338667 16.512c-30.378667 72.277333-102.016 118.954667-182.656 118.954666"
                        fill="#ffffff" p-id="3808"></path><path
                        d="M303.829333 627.456c-49.92 0-90.453333 41.088-90.453333 91.605333C213.333333 769.578667 253.866667 810.666667 303.786667 810.666667h416.341333C770.133333 810.666667 810.666667 769.578667 810.666667 719.061333c0-50.517333-40.533333-91.605333-90.453334-91.605333H303.786667zM720.213333 853.333333H303.829333C230.442667 853.333333 170.709333 793.088 170.709333 719.061333 170.666667 645.034667 230.4 584.789333 303.786667 584.789333h416.341333C793.6 584.789333 853.333333 645.034667 853.333333 719.061333 853.333333 793.088 793.6 853.333333 720.213333 853.333333z"
                        fill="#ffffff" p-id="3809"></path></svg>
                </span>
            <span>用户名</span>
            <input maxlength="16" placeholder="   UserName" type="text" v-model="loginAdminname">
          </label>
        </div>
        <div class="password">
          <label>
                <span>
                    <svg class="icon" height="20" p-id="4677" t="1586443029761"
                         version="1.1" viewBox="0 0 1024 1024" width="20" xmlns="http://www.w3.org/2000/svg"><path
                        d="M511.176404 551.030949c-38.584889 0-69.974626 31.392323-69.974626 69.979798 0 15.341899 4.874343 29.909333 14.105859 42.121051 0.192646 0.261172 0.404687 0.513293 0.616727 0.768 17.287758 20.146424 18.545778 34.525091 18.620768 35.842586l0 39.965737c0 20.20202 16.434424 36.631273 36.631273 36.631273 20.20202 0 36.632566-16.434424 36.632566-36.631273l0-40.718222c0-0.457697-0.015515-0.919273-0.060768-1.373091 0-0.03103 0.090505-0.005172 0.095677-0.040081 0.100848-0.919273 0.408566-2.884525 1.343354-5.727677 1.883798-5.712162 6.30303-15.080727 16.707232-27.24202 0.151273-0.177131 0.297374-0.359434 0.439596-0.540444 9.702141-12.399192 14.828606-27.288566 14.828606-43.055838C581.161374 582.424566 549.767758 551.030949 511.176404 551.030949L511.176404 551.030949zM541.580929 644.313212c-24.46998 28.748283-25.667232 49.334303-25.434505 55.091717l0 40.308364c0 2.737131-2.232889 4.963556-4.97002 4.963556-2.737131 0-4.964848-2.226424-4.964848-4.963556L506.211556 699.552323c0-2.954343-0.81196-26.410667-25.903838-55.858424-4.873051-6.601697-7.444687-14.429091-7.444687-22.681859 0-21.126465 17.186909-38.319838 38.313374-38.319838 21.131636 0 38.323717 17.193374 38.323717 38.319838C549.500121 629.536323 546.76299 637.586101 541.580929 644.313212L541.580929 644.313212zM541.580929 644.313212"
                        fill="#ffffff" p-id="4678"></path><path
                        d="M777.580606 417.122263l-1.989818 0 0-0.232727-36.848485 0 0-101.520808c0-0.67103-0.045253-1.343354-0.126707-2.014384-6.923636-54.061253-33.298101-103.833859-74.272323-140.166465C623.009616 136.546263 569.792646 116.363636 514.505697 116.363636c-60.353939 0-117.095434 23.505455-159.773737 66.177293-42.67701 42.67701-66.176 99.419798-66.176 159.772444l0 74.78303-43.132121 0.025859c-29.812364 0-54.070303 24.256646-54.070303 54.070303l0 383.171232c0 29.818828 24.257939 54.071596 54.070303 54.071596l330.369293 0c0.479677 0 0.954182-0.020687 1.424808-0.065939l198.93398 0c0.469333 0.045253 0.943838 0.065939 1.428687 0.065939 29.808485 0 54.065131-24.252768 54.065131-54.071596L831.645737 471.192566C831.64703 441.378909 807.394263 417.122263 777.580606 417.122263L777.580606 417.122263zM320.246949 342.313374c0-51.889131 20.207192-100.671354 56.899232-137.358222 36.686869-36.69204 85.470384-56.899232 137.358222-56.899232 47.530667 0 93.282263 17.348525 128.806788 48.848162 34.990545 31.021253 57.597414 73.420283 63.733657 119.489939l0 100.495515-386.799192 0.232727L320.245657 342.313374 320.246949 342.313374zM799.954747 854.363798c0 11.773414-9.135838 21.449697-20.69204 22.313374l-0.126707 0L245.423838 876.739232c-12.338424 0-22.37802-10.03701-22.37802-22.375434L223.045818 471.192566c0-12.338424 10.039596-22.379313 22.37802-22.379313l58.984727-0.029737c0.808081 0 1.596768-0.081455 2.373818-0.196525 0.849455 0.135758 1.71701 0.231434 2.606545 0.231434l299.146343 0c0.288323 0.005172 0.731798 0.015515 1.313616 0.015515 1.247677 0 3.152162-0.029737 5.590626-0.156444l105.742222 0c0.656808 0.080162 1.322667 0.135758 2.000162 0.135758l54.398707 0c12.333253 0 22.374141 10.046061 22.374141 22.379313L799.954747 854.363798 799.954747 854.363798zM799.954747 854.363798"
                        fill="#ffffff" p-id="4679"></path></svg>
                </span>
            <span>密码</span>
            <input maxlength="16" placeholder="   Password" type="password" v-model="loginAdminPassword">
          </label>
        </div>
        <div class="buttons2">
          <div @click="check2" class="enterBut">
            <span>登入</span>
          </div>
          <div @click="out" class="enterBut" style="margin-left: 10px;">
            <span>退出</span>
          </div>
        </div>
      </div>

    <div v-if="registerDialogVisible"  class="loginFrame">
      <p class="loginTitle">Register</p>
      <div  class="user">
        <label>
                <span>
                    <svg class="icon" height="20" p-id="3807" t="1586442913188"
                         version="1.1" viewBox="0 0 1024 1024" width="20" xmlns="http://www.w3.org/2000/svg"><path
                      d="M517.632 552.149333c-108.714667 0-197.162667-85.546667-197.162667-190.72 0-38.314667 11.690667-75.306667 33.877334-106.922666C391.04 202.026667 452.138667 170.666667 517.632 170.666667c65.408 0 126.464 31.274667 163.2 83.712 7.765333 11.093333 14.250667 22.869333 19.413333 35.072a21.333333 21.333333 0 1 1-39.338666 16.64 147.285333 147.285333 0 0 0-15.018667-27.221334C617.130667 237.824 569.173333 213.333333 517.632 213.333333c-51.626667 0-99.584 24.533333-128.426667 65.621334a143.445333 143.445333 0 0 0-26.069333 82.432c0 81.664 69.290667 148.096 154.453333 148.096 63.402667 0 119.722667-36.437333 143.36-92.8a21.333333 21.333333 0 0 1 39.338667 16.512c-30.378667 72.277333-102.016 118.954667-182.656 118.954666"
                      fill="#ffffff" p-id="3808"></path><path
                      d="M303.829333 627.456c-49.92 0-90.453333 41.088-90.453333 91.605333C213.333333 769.578667 253.866667 810.666667 303.786667 810.666667h416.341333C770.133333 810.666667 810.666667 769.578667 810.666667 719.061333c0-50.517333-40.533333-91.605333-90.453334-91.605333H303.786667zM720.213333 853.333333H303.829333C230.442667 853.333333 170.709333 793.088 170.709333 719.061333 170.666667 645.034667 230.4 584.789333 303.786667 584.789333h416.341333C793.6 584.789333 853.333333 645.034667 853.333333 719.061333 853.333333 793.088 793.6 853.333333 720.213333 853.333333z"
                      fill="#ffffff" p-id="3809"></path></svg>
                </span>
          <span>用户名</span>
          <input maxlength="16" placeholder="   UserName" type="text" v-model="registerUsername">
        </label>
      </div>
      <div class="password">
        <label>
                <span>
                    <svg class="icon" height="20" p-id="4677" t="1586443029761"
                         version="1.1" viewBox="0 0 1024 1024" width="20" xmlns="http://www.w3.org/2000/svg"><path
                      d="M511.176404 551.030949c-38.584889 0-69.974626 31.392323-69.974626 69.979798 0 15.341899 4.874343 29.909333 14.105859 42.121051 0.192646 0.261172 0.404687 0.513293 0.616727 0.768 17.287758 20.146424 18.545778 34.525091 18.620768 35.842586l0 39.965737c0 20.20202 16.434424 36.631273 36.631273 36.631273 20.20202 0 36.632566-16.434424 36.632566-36.631273l0-40.718222c0-0.457697-0.015515-0.919273-0.060768-1.373091 0-0.03103 0.090505-0.005172 0.095677-0.040081 0.100848-0.919273 0.408566-2.884525 1.343354-5.727677 1.883798-5.712162 6.30303-15.080727 16.707232-27.24202 0.151273-0.177131 0.297374-0.359434 0.439596-0.540444 9.702141-12.399192 14.828606-27.288566 14.828606-43.055838C581.161374 582.424566 549.767758 551.030949 511.176404 551.030949L511.176404 551.030949zM541.580929 644.313212c-24.46998 28.748283-25.667232 49.334303-25.434505 55.091717l0 40.308364c0 2.737131-2.232889 4.963556-4.97002 4.963556-2.737131 0-4.964848-2.226424-4.964848-4.963556L506.211556 699.552323c0-2.954343-0.81196-26.410667-25.903838-55.858424-4.873051-6.601697-7.444687-14.429091-7.444687-22.681859 0-21.126465 17.186909-38.319838 38.313374-38.319838 21.131636 0 38.323717 17.193374 38.323717 38.319838C549.500121 629.536323 546.76299 637.586101 541.580929 644.313212L541.580929 644.313212zM541.580929 644.313212"
                      fill="#ffffff" p-id="4678"></path><path
                      d="M777.580606 417.122263l-1.989818 0 0-0.232727-36.848485 0 0-101.520808c0-0.67103-0.045253-1.343354-0.126707-2.014384-6.923636-54.061253-33.298101-103.833859-74.272323-140.166465C623.009616 136.546263 569.792646 116.363636 514.505697 116.363636c-60.353939 0-117.095434 23.505455-159.773737 66.177293-42.67701 42.67701-66.176 99.419798-66.176 159.772444l0 74.78303-43.132121 0.025859c-29.812364 0-54.070303 24.256646-54.070303 54.070303l0 383.171232c0 29.818828 24.257939 54.071596 54.070303 54.071596l330.369293 0c0.479677 0 0.954182-0.020687 1.424808-0.065939l198.93398 0c0.469333 0.045253 0.943838 0.065939 1.428687 0.065939 29.808485 0 54.065131-24.252768 54.065131-54.071596L831.645737 471.192566C831.64703 441.378909 807.394263 417.122263 777.580606 417.122263L777.580606 417.122263zM320.246949 342.313374c0-51.889131 20.207192-100.671354 56.899232-137.358222 36.686869-36.69204 85.470384-56.899232 137.358222-56.899232 47.530667 0 93.282263 17.348525 128.806788 48.848162 34.990545 31.021253 57.597414 73.420283 63.733657 119.489939l0 100.495515-386.799192 0.232727L320.245657 342.313374 320.246949 342.313374zM799.954747 854.363798c0 11.773414-9.135838 21.449697-20.69204 22.313374l-0.126707 0L245.423838 876.739232c-12.338424 0-22.37802-10.03701-22.37802-22.375434L223.045818 471.192566c0-12.338424 10.039596-22.379313 22.37802-22.379313l58.984727-0.029737c0.808081 0 1.596768-0.081455 2.373818-0.196525 0.849455 0.135758 1.71701 0.231434 2.606545 0.231434l299.146343 0c0.288323 0.005172 0.731798 0.015515 1.313616 0.015515 1.247677 0 3.152162-0.029737 5.590626-0.156444l105.742222 0c0.656808 0.080162 1.322667 0.135758 2.000162 0.135758l54.398707 0c12.333253 0 22.374141 10.046061 22.374141 22.379313L799.954747 854.363798 799.954747 854.363798zM799.954747 854.363798"
                      fill="#ffffff" p-id="4679"></path></svg>
                </span>
          <span>旧密码</span>
          <input maxlength="16" placeholder="   Password" type="password" v-model="registerPassword">
        </label>
      </div>
      <div class="confirm_password">
        <label>
                <span>
                    <svg class="icon" height="20" p-id="4677" t="1586443029761"
                         version="1.1" viewBox="0 0 1024 1024" width="20" xmlns="http://www.w3.org/2000/svg"><path
                      d="M511.176404 551.030949c-38.584889 0-69.974626 31.392323-69.974626 69.979798 0 15.341899 4.874343 29.909333 14.105859 42.121051 0.192646 0.261172 0.404687 0.513293 0.616727 0.768 17.287758 20.146424 18.545778 34.525091 18.620768 35.842586l0 39.965737c0 20.20202 16.434424 36.631273 36.631273 36.631273 20.20202 0 36.632566-16.434424 36.632566-36.631273l0-40.718222c0-0.457697-0.015515-0.919273-0.060768-1.373091 0-0.03103 0.090505-0.005172 0.095677-0.040081 0.100848-0.919273 0.408566-2.884525 1.343354-5.727677 1.883798-5.712162 6.30303-15.080727 16.707232-27.24202 0.151273-0.177131 0.297374-0.359434 0.439596-0.540444 9.702141-12.399192 14.828606-27.288566 14.828606-43.055838C581.161374 582.424566 549.767758 551.030949 511.176404 551.030949L511.176404 551.030949zM541.580929 644.313212c-24.46998 28.748283-25.667232 49.334303-25.434505 55.091717l0 40.308364c0 2.737131-2.232889 4.963556-4.97002 4.963556-2.737131 0-4.964848-2.226424-4.964848-4.963556L506.211556 699.552323c0-2.954343-0.81196-26.410667-25.903838-55.858424-4.873051-6.601697-7.444687-14.429091-7.444687-22.681859 0-21.126465 17.186909-38.319838 38.313374-38.319838 21.131636 0 38.323717 17.193374 38.323717 38.319838C549.500121 629.536323 546.76299 637.586101 541.580929 644.313212L541.580929 644.313212zM541.580929 644.313212"
                      fill="#ffffff" p-id="4678"></path><path
                      d="M777.580606 417.122263l-1.989818 0 0-0.232727-36.848485 0 0-101.520808c0-0.67103-0.045253-1.343354-0.126707-2.014384-6.923636-54.061253-33.298101-103.833859-74.272323-140.166465C623.009616 136.546263 569.792646 116.363636 514.505697 116.363636c-60.353939 0-117.095434 23.505455-159.773737 66.177293-42.67701 42.67701-66.176 99.419798-66.176 159.772444l0 74.78303-43.132121 0.025859c-29.812364 0-54.070303 24.256646-54.070303 54.070303l0 383.171232c0 29.818828 24.257939 54.071596 54.070303 54.071596l330.369293 0c0.479677 0 0.954182-0.020687 1.424808-0.065939l198.93398 0c0.469333 0.045253 0.943838 0.065939 1.428687 0.065939 29.808485 0 54.065131-24.252768 54.065131-54.071596L831.645737 471.192566C831.64703 441.378909 807.394263 417.122263 777.580606 417.122263L777.580606 417.122263zM320.246949 342.313374c0-51.889131 20.207192-100.671354 56.899232-137.358222 36.686869-36.69204 85.470384-56.899232 137.358222-56.899232 47.530667 0 93.282263 17.348525 128.806788 48.848162 34.990545 31.021253 57.597414 73.420283 63.733657 119.489939l0 100.495515-386.799192 0.232727L320.245657 342.313374 320.246949 342.313374zM799.954747 854.363798c0 11.773414-9.135838 21.449697-20.69204 22.313374l-0.126707 0L245.423838 876.739232c-12.338424 0-22.37802-10.03701-22.37802-22.375434L223.045818 471.192566c0-12.338424 10.039596-22.379313 22.37802-22.379313l58.984727-0.029737c0.808081 0 1.596768-0.081455 2.373818-0.196525 0.849455 0.135758 1.71701 0.231434 2.606545 0.231434l299.146343 0c0.288323 0.005172 0.731798 0.015515 1.313616 0.015515 1.247677 0 3.152162-0.029737 5.590626-0.156444l105.742222 0c0.656808 0.080162 1.322667 0.135758 2.000162 0.135758l54.398707 0c12.333253 0 22.374141 10.046061 22.374141 22.379313L799.954747 854.363798 799.954747 854.363798zM799.954747 854.363798"
                      fill="#ffffff" p-id="4679"></path></svg>
                </span>
          <span>新密码</span>
          <input maxlength="16" placeholder="   Password" type="password"v-model="confirmPassword">
        </label>
      </div>
      <div class="buttons2">
        <div @click="check" class="enterBut">
          <span>修改密码</span>
        </div>
        <div @click="out" class="enterBut" style="margin-left: 10px;">
          <span>退出</span>
        </div>
      </div>
    </div>
  </div>

    <div class="stars" ref="starsRef">
      <div class="star" v-for="(item, index) in starsCount" :key="index"></div>
    </div>
  </div>

</template>

<script>
import { onMounted, ref } from "vue";
import axios from 'axios';
export default {
  name: 'Login',
  data() {

    return {
      button1Visible: true,
      button2Visible: true,

      loginDialogVisible: false,
      registerDialogVisible: false,
      admDialogVisible:false,
      loginUsername: "",
      loginPassword: "",
      loginAdminname: "",
      loginAdminPassword: "",

      registerUsername: "",
      registerPassword: "",
      confirmPassword: "",
    };
  },
  methods: {
    showLoginDialog() {
      console.log("showLoginDialog method called")
      this.loginDialogVisible = true;
      this.button1Visible=false;
      this.button2Visible =false;
    },


    login() {
      //去后台验证用户密码
      const formData = new FormData();
      formData.append('username', this.loginUsername);
      formData.append('password', this.loginPassword);
      console.log(formData)

      this.$axios.post(this.$httpUrl+'/auth/login', formData, {
        withCredentials: true,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      }).then(res => {
        console.log(res);

        if (res.data.code === 2000) {
          console.log(res.data.msg);
          console.log("1");
          sessionStorage.setItem("CurUser", JSON.stringify(res.data.data));
          localStorage.setItem("CurUser", JSON.stringify(res.data.data));
          console.log(JSON.parse(sessionStorage.getItem('CurUser')));
          this.$message.success(res.data.msg);
          //获取用户的完整信息，存入sessionStorage
            this.$axios.get(this.$httpUrl+'/user/sid/'+JSON.parse(sessionStorage.getItem('CurUser')).username,{
                withCredentials: true,
                headers:{
                    'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                }}
            ).then(res=>{
                if (res.data.code===2010) {
                    sessionStorage.setItem("UserData", JSON.stringify(res.data.data));
                    console.log(JSON.parse(sessionStorage.getItem('UserData')));

                    this.$router.push({ name:'index' });
                } else {
                  this.$message.warning(res.data.msg);
                  this.$router.push({ name:'index' });
                  console.log(111111)

                    // 登录失败，可以显示错误消息
                }
            })
        } else {
          this.$message.warning(res.data.msg);
          console.log(res.data.msg);
          this.out()
        }
      });
    },

    check2() {


      const formData = new FormData();
      formData.append('username', this.loginAdminname);
      formData.append('password', this.loginAdminPassword);
      console.log('user login:' + formData)

      this.$axios.post(this.$httpUrl+'/auth/login', formData, {
        withCredentials: true,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      }).then(res => {
        console.log(res);

        if (res.data.code === 2000) {
          this.$message.success(res.data.msg);
          this.$router.push({ name:'indexAdmin' });
          console.log(res.data.msg);
          console.log("1");
          sessionStorage.setItem("admin", JSON.stringify(res.data.data));
          localStorage.setItem("admin", JSON.stringify(res.data.data));
          console.log(JSON.parse(sessionStorage.getItem('admin')));
        } else {
          console.log(res.msg);
          this.$message.warning(res.data.msg);
        }
      });
    },

    check() {
      const formData1 = new FormData();
      formData1.append('username', this.registerUsername);
      formData1.append('password', this.registerPassword);
      console.log(formData1)
      this.$axios.post(this.$httpUrl+'/auth/login', formData1, {
        withCredentials: true,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      }).then(res => {
        if (res.data.code === 2000) {
          sessionStorage.setItem("CurUser", JSON.stringify(res.data.data));
          localStorage.setItem("CurUser", JSON.stringify(res.data.data));
          console.log(JSON.parse(sessionStorage.getItem('CurUser')));
          this.$message.success(res.data.msg);
          //获取用户的完整信息，存入sessionStorage
          this.$axios.get(this.$httpUrl+'/user/'+JSON.parse(sessionStorage.getItem('CurUser')).id, {
            withCredentials: true,
            headers:{
              'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
            }}
          ).then(res=>{
            if (res.data.code===2010) {
              sessionStorage.setItem("UserData", JSON.stringify(res.data.data));
              console.log(JSON.parse(sessionStorage.getItem('UserData')));


              this.$axios.put(this.$httpUrl+'/user', {
                id:  this.registerUsername,
                password: this.confirmPassword,

              }, {
                withCredentials: true,
                headers:{
                  'Authorization':"Bearer"+" "+JSON.parse(sessionStorage.getItem('CurUser')).token
                }
              }).then(res => {
                console.log(res);

                if (res.data.code === 2020) {
                  this.$message.success(res.data.msg);
                  this.out()
                } else {
                  this.$message.warning(res.data.msg);
                }
              });
            } else {


              // 登录失败，可以显示错误消息
            }
          })
        } else {
          this.$message.warning(res.data.msg);
          console.log(res.data.msg);
        }
      });

    },
    out() {
      this.loginDialogVisible = false;
      this.registerDialogVisible= false;
      this.admDialogVisible=false;
      this.button1Visible=true;
      this.button2Visible =true;
    },
    showRegisterDialog() {
      this.registerDialogVisible = true;
      this.button1Visible=false;
      this.button2Visible =false;
    },
    showAdminDialog(){
      this.admDialogVisible = true;
      this.button1Visible=false;
      this.button2Visible =false;
    },

    closeRegisterDialog() {
      this.registerDialogVisible = false;
    },
  },


  setup() {
    let starsRef = ref(null);
    const starsCount = 800; //星星数量
    const distance = 900; //间距

    onMounted(() => {
      let starNodes = Array.from(starsRef.value.children);
      starNodes.forEach((item) => {
        let speed = 0.2 + Math.random() * 1;
        let thisDistance = distance + Math.random() * 300;
        item.style.transformOrigin = `0 0 ${thisDistance}px`;
        item.style.transform = `
        translate3d(0,0,-${thisDistance}px)
        rotateY(${Math.random() * 360}deg)
        rotateX(${Math.random() * -50}deg)
        scale(${speed},${speed})`;
      });
    });

    return {
      starsRef,
      starsCount,
    };
  },
};
</script>

<style lang="css" scoped>
.buttons {
  text-align: center;
  margin-top: 100px;
}
.buttons2 {
  display: flex;
  justify-content: space-between;
}
.buttons3 {
  display: flex;
  justify-content: center; /* 居中对齐 */
  align-items: center; /* 垂直居中对齐 */

}

button {
  margin: 10px;
  padding: 10px 20px;
  font-size: 16px;
}
.enterBut {
  width: 200px;
  height: 50px;
  border-radius: 50px;
  background-image: linear-gradient(to right, #79ccd4, #71acfa);
  text-align: center;
  padding-top: 10px;
  box-sizing: border-box;
  font-size: 20px;
  cursor: pointer;
}
.title-container {
  display: flex; /* 使用flex布局 */
  align-items: center; /* 将内容垂直居中 */
  text-align: center; /* 居中标题文本 */
  padding: 20px; /* 添加内边距以增加标题的可读性 */
  height: 500px; /* 设置容器的高度为 50 像素，您可以根据需要调整这个值 */
}


.conference-title {

  text-align: center;
  font-size: 80px !important;
  font-weight: bold;
  padding: 20px;
  margin: 0 auto !important;
  color: white; /* 将字体颜色设置为白色 */
}
.body {
  position: absolute;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  background: radial-gradient(
    200% 100% at bottom center,
    #b6d0f7,
    #6fa0e9,
    #1b3f47
  );
  background: radial-gradient(
    200% 105% at top center,
    #1b1d47 10%,
    #516b7d 40%,
    #6fbee9 65%,
    #b6f7e8
  );

  background-attachment: fixed;
  overflow: hidden;
}

@keyframes rotate {
  0% {
    transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(0);
  }
  100% {
    transform: perspective(400px) rotateZ(20deg) rotateX(-40deg)
    rotateY(-360deg);
  }
}
.stars {
  transform: perspective(500px);
  transform-style: preserve-3d;
  position: absolute;
  perspective-origin: 50% 100%;
  left: 45%;
  animation: rotate 90s infinite linear;
  bottom: 0;
}
.star {
  width: 2px;
  height: 2px;
  background: #f7f7b6;
  position: absolute;
  left: 0;
  top: 0;
  backface-visibility: hidden;


}
* {
  margin: 0;
  padding: 0;
}

html, body {
  width: 100%;
  height: 100%;
}

.loginFrame {
  width: 700px;
  height: 500px;
  background: rgba(0, 0, 0, 0.3);
  /*元素上下左右都居中*/
  position: fixed;
  margin: auto;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  border-radius: 10px;
  padding: 50px 0;
  box-sizing: border-box;
  box-shadow: 0 0 5px 5px rgba(0, 0, 0, 0.4);
}
.loginFrame > div {
  width: 350px;
  height: 50px;
  margin: 40px auto;
  color: white;
}

.loginFrame > p {
  text-align: center;
  color: white;
  font-size: 25px;
}

.loginFrame > div span {
  display: inline-block;
  cursor: pointer;
}

.loginFrame > div input {
  width: 100%;
  height: 30px;
  background: transparent;
  border: none;
  border-bottom: 1px solid white;
  outline: none;
  color: white;

}

.loginFrame .enterBut {
  width: 200px;
  height: 50px;
  border-radius: 50px;
  background-image: linear-gradient(to right, #79ccd4, #71acfa);
  text-align: center;
  padding-top: 10px;
  box-sizing: border-box;
  font-size: 20px;
  cursor: pointer;
}

.loginFrame .enterBut:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, .5) inset;
}

input::-webkit-input-placeholder { /* WebKit browsers 适配谷歌 */
  color: #BDCADA;
}

svg {
  vertical-align: bottom;
}
</style>
