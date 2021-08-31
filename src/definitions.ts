export interface ZendeskChatPlugin {
  initialize(): void;
  open(): void;
  setVisitorInfo(): void;
}
