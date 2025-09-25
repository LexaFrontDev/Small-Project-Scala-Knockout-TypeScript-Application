import * as ko from "knockout";
import { RegisterFetch } from "../../service/fetch/auth/RegisterFetch";
import { RegisterFetchProps } from "../../props/auth/register/RegisterFetchProps";

let registerService = new RegisterFetch();

export class RegisterPage {
    name = ko.observable("");
    email = ko.observable("");
    subscribe = ko.observable(false);
    role = ko.observable("user");
    records = ko.observableArray<any>([]);
    errors = ko.observableArray<string>([]);

    constructor() {
        this.render();
    }

    validate = (): boolean => {
        this.errors([]);

        if (!this.name() || this.name().trim().length < 2) {
            this.errors.push("Имя должно быть минимум 2 символа");
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!this.email() || !emailRegex.test(this.email())) {
            this.errors.push("Email некорректный");
        }

        return this.errors().length === 0;
    }

    saveData = async () => {
        if (!this.validate()) return false;

        const payload: RegisterFetchProps = {
            name: this.name(),
            email: this.email(),
            subscribe: this.subscribe(),
            role: this.role()
        };

        const result =  await registerService.register(payload);

        if (!result) {
            this.errors.push("Не удалось сохранить данные или пользователь уже существует");
            return false;
        }

        this.name("");
        this.email("");
        this.subscribe(false);
        this.role("user");

        if (result) {
            window.location.href = "/dashboard";
            return false;
        }
        return false;
    }



    render() {
        const appDiv = document.createElement("div");
        appDiv.id = "src";
        document.body.appendChild(appDiv);

        appDiv.innerHTML = `
            <form data-bind="submit: saveData" class="space-y-2">
                <div data-bind="foreach: errors">
                    <p class="text-red-500" data-bind="text: $data"></p>
                </div>
                <input placeholder="Имя" data-bind="value: name" class="border p-1 w-full"/><br/>
                <input placeholder="Email" data-bind="value: email" class="border p-1 w-full"/><br/>
                <label>
                    <input type="checkbox" data-bind="checked: subscribe" /> Подписка
                </label><br/>
                <select data-bind="value: role" class="border p-1 w-full">
                    <option value="user">User</option>
                    <option value="admin">Admin</option>
                </select><br/>
                <button type="submit" class="bg-blue-500 text-white px-3 py-1 rounded">Сохранить</button>
            </form>
        `;
        ko.applyBindings(this, appDiv);
    }
}
