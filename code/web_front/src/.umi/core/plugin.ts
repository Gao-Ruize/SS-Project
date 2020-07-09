// @ts-nocheck
import { Plugin } from 'D:/SS-Project/SS-Project/code/web_front/node_modules/@umijs/runtime';

const plugin = new Plugin({
  validKeys: ['patchRoutes','rootContainer','render','onRouteChange','getInitialState','locale','locale','layout','request',],
});
plugin.register({
  apply: require('D:/SS-Project/SS-Project/code/web_front/src/app.tsx'),
  path: 'D:/SS-Project/SS-Project/code/web_front/src/app.tsx',
});
plugin.register({
  apply: require('D:/SS-Project/SS-Project/code/web_front/node_modules/umi-plugin-antd-icon-config/lib/app.js'),
  path: 'D:/SS-Project/SS-Project/code/web_front/node_modules/umi-plugin-antd-icon-config/lib/app.js',
});
plugin.register({
  apply: require('D:/SS-Project/SS-Project/code/web_front/src/.umi/plugin-access/rootContainer.ts'),
  path: 'D:/SS-Project/SS-Project/code/web_front/src/.umi/plugin-access/rootContainer.ts',
});
plugin.register({
  apply: require('../plugin-initial-state/runtime'),
  path: '../plugin-initial-state/runtime',
});
plugin.register({
  apply: require('D:/SS-Project/SS-Project/code/web_front/src/.umi/plugin-locale/runtime.tsx'),
  path: 'D:/SS-Project/SS-Project/code/web_front/src/.umi/plugin-locale/runtime.tsx',
});
plugin.register({
  apply: require('@@/plugin-layout/runtime.tsx'),
  path: '@@/plugin-layout/runtime.tsx',
});
plugin.register({
  apply: require('../plugin-model/runtime'),
  path: '../plugin-model/runtime',
});

export { plugin };
