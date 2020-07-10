// @ts-nocheck
import { ApplyPluginsType } from 'D:/SS-Project/code/web_front/node_modules/@umijs/runtime';
import { plugin } from './plugin';

const routes = [
  {
    "path": "/HomePage",
    "exact": true,
    "component": require('@/pages/HomePage.jsx').default
  }
];

// allow user to extend routes
plugin.applyPlugins({
  key: 'patchRoutes',
  type: ApplyPluginsType.event,
  args: { routes },
});

export { routes };
