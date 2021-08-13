export interface ZendeskChatPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  initialize(): void;
  open(): void;
  setVisitorInfo(): void;
}
