import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '2f87772e7922654cfdfb2869e9bcbce32966ef45294ec9ccf15e37ebcbe1854b') {
    pending.push(import('./chunks/chunk-2f87772e7922654cfdfb2869e9bcbce32966ef45294ec9ccf15e37ebcbe1854b.js'));
  }
  if (key === '4c9d8a2e2a4f41b483ca04dd6bd20736309fada477eb7293aa2aed7934eab1b4') {
    pending.push(import('./chunks/chunk-4c9d8a2e2a4f41b483ca04dd6bd20736309fada477eb7293aa2aed7934eab1b4.js'));
  }
  if (key === '3a1568228fa661d232f497b34242acb80f0659d74aa20fbb5d693dda306856b0') {
    pending.push(import('./chunks/chunk-3a1568228fa661d232f497b34242acb80f0659d74aa20fbb5d693dda306856b0.js'));
  }
  if (key === 'ed8409f6c3216a097a4dc3d5f39b75999927aadd884775519702a81582f4dde8') {
    pending.push(import('./chunks/chunk-ed8409f6c3216a097a4dc3d5f39b75999927aadd884775519702a81582f4dde8.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;