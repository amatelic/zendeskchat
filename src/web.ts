import { WebPlugin } from '@capacitor/core';

import type { ZendeskChatPlugin } from './definitions';

export class ZendeskChatWeb extends WebPlugin implements ZendeskChatPlugin {

  initialize() {
    console.log('init')
  }
  open() {
    console.log('open')
  }
  setVisitorInfo() {
    console.log('set info')
  }
}
