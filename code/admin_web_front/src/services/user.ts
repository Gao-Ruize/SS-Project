// import { request } from 'umi';
import { login_info } from '@/services/login';

// export async function query() {
//   return request<API.CurrentUser[]>('/api/users');
// }

export async function queryCurrent() {
  const ret = new Promise(function(resolve){
    const val = { name: login_info.user };
    resolve(val);
  })
  return ret;
  // return {name: login_info.user};
  // console.log(await request<API.CurrentUser>('/api/currentUser'))
  // return request<API.CurrentUser>('/api/currentUser');
}

// export async function queryNotices(): Promise<any> {
//   return request<{ data: API.NoticeIconData[] }>('/api/notices');
// }
