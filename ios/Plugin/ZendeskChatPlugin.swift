import Foundation
import Capacitor
import ZDCChat

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(ZendeskChatPlugin)
public class ZendeskChatPlugin: CAPPlugin {
    private let implementation = ZendeskChat()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }

    @objc func initialize(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            let accountKey = self.getConfigValue("accountKey") as? String
            ZDCChat.initialize(withAccountKey: accountKey)
        }
    }

    @objc func setVisitorInfo(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            let name = call.getString("name")
            let email = call.getString("email")
            let phone = call.getString("phoneNumber")
            ZDCChat.updateVisitor { user in
                user?.name = name
                user?.email = email
                user?.phone = phone
            }
        }
    }

    @objc func open(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            let department = call.getString("department") ?? "testing"
            let tags = call.getArray("tags", String.self) ?? [];
            ZDCChat.start(in: self.bridge?.viewController?.navigationController, withConfig: {config in
                config?.department = department
                config?.tags = tags
            })
        }
    }
}
