// @ts-nocheck
import { Plugin } from 'D:/SS-Project/code/web_front/node_modules/@umijs/runtime';

const plugin = new Plugin({
  validKeys: ['patchRoutes','rootContainer','render','onRouteChange',],
});
plugin.register({
  apply: require('D:/SS-Project/code/web_front/src/app.js'),
  path: 'D:/SS-Project/code/web_front/src/app.js',
});

export { plugin };
