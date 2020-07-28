import { request } from 'umi';

export interface LoginParamsType {
  username: string;
  password: string;
  mobile: string;
  captcha: string;
  type: string;
}

export async function fakeAccountLogin(params: LoginParamsType) {
  // return request<API.LoginStateType>('/api/login/account', {
  //   method: 'POST',
  //   data: params,
  // });

  console.log(`params:${params}`);
  const info = {
    realId: params.username,
    openId: params.password,
  }
  const jsonstr = JSON.stringify(info);
  let returnMsg = { status: "error", token: '', type: '', currentAuthority: '' };
  await $.ajax({
    type: 'post',
    url: 'http://localhost:8443/api/admin/login',
    contentType: "application/json;charset=utf-8;",
    data: jsonstr,
    dataType: "text",
    success(data) {
      console.log(data);
      const result = JSON.parse(data);
      if(result.code === 200){
        returnMsg = { status: "ok", token: result.token, type: 'account', currentAuthority: 'admin' };
      }else{
        returnMsg = { status: "error", token: '', type: '', currentAuthority: '' };
      }
    },
    error(error) {
      console.log(`error: ${error}`);
      returnMsg = { status: "error", token: '', type: '', currentAuthority: '' };
    }
  })
  return returnMsg;

}

export async function getFakeCaptcha(mobile: string) {
  return request(`/api/login/captcha?mobile=${mobile}`);
}

export async function outLogin() {
  return request('/api/login/outLogin');
}
