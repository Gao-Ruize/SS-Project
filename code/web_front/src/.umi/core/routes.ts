// @ts-nocheck
import { ApplyPluginsType, dynamic } from 'D:/SS-Project/SS-Project/code/web_front/node_modules/@umijs/runtime';
import { plugin } from './plugin';

const routes = [
  {
    "path": "/",
    "component": dynamic({ loader: () => import(/* webpackChunkName: '.umi__plugin-layout__Layout' */'D:/SS-Project/SS-Project/code/web_front/src/.umi/plugin-layout/Layout.tsx'), loading: require('@/components/PageLoading/index').default}),
    "routes": [
      {
        "path": "/user",
        "layout": false,
        "routes": [
          {
            "name": "login",
            "path": "/user/login",
            "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__user__login' */'D:/SS-Project/SS-Project/code/web_front/src/pages/user/login'), loading: require('@/components/PageLoading/index').default}),
            "exact": true
          }
        ]
      },
      {
        "path": "/welcome",
        "name": "welcome",
        "icon": "smile",
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__Welcome' */'D:/SS-Project/SS-Project/code/web_front/src/pages/Welcome'), loading: require('@/components/PageLoading/index').default}),
        "exact": true
      },
      {
        "path": "/admin",
        "name": "admin",
        "icon": "crown",
        "access": "canAdmin",
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__Admin' */'D:/SS-Project/SS-Project/code/web_front/src/pages/Admin'), loading: require('@/components/PageLoading/index').default}),
        "routes": [
          {
            "path": "/admin/sub-page",
            "name": "sub-page",
            "icon": "smile",
            "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__Welcome' */'D:/SS-Project/SS-Project/code/web_front/src/pages/Welcome'), loading: require('@/components/PageLoading/index').default}),
            "exact": true
          }
        ]
      },
      {
        "name": "list.table-list",
        "icon": "table",
        "path": "/list",
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__ListTableList' */'D:/SS-Project/SS-Project/code/web_front/src/pages/ListTableList'), loading: require('@/components/PageLoading/index').default}),
        "exact": true
      },
      {
        "path": "/",
        "redirect": "/welcome",
        "exact": true
      },
      {
        "name": "学生列表",
        "icon": "smile",
        "path": "/students",
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__StudentListPage' */'D:/SS-Project/SS-Project/code/web_front/src/pages/StudentListPage'), loading: require('@/components/PageLoading/index').default}),
        "exact": true
      },
      {
        "name": "导师列表",
        "icon": "smile",
        "path": "/tutors",
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__TutorListPage' */'D:/SS-Project/SS-Project/code/web_front/src/pages/TutorListPage'), loading: require('@/components/PageLoading/index').default}),
        "exact": true
      },
      {
        "name": "消息列表",
        "icon": "table",
        "path": "/messages",
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__MsgListPage' */'D:/SS-Project/SS-Project/code/web_front/src/pages/MsgListPage'), loading: require('@/components/PageLoading/index').default}),
        "exact": true
      },
      {
        "component": dynamic({ loader: () => import(/* webpackChunkName: 'p__404' */'D:/SS-Project/SS-Project/code/web_front/src/pages/404'), loading: require('@/components/PageLoading/index').default}),
        "exact": true
      }
    ]
  }
];

// allow user to extend routes
plugin.applyPlugins({
  key: 'patchRoutes',
  type: ApplyPluginsType.event,
  args: { routes },
});

export { routes };
