import { WebPlugin } from '@capacitor/core';

import type { ZendeskChatPlugin } from './definitions';

export class ZendeskChatWeb extends WebPlugin implements ZendeskChatPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

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
