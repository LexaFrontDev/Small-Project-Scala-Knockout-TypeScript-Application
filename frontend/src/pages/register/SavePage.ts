import * as ko from "knockout";
import { SaveFetch } from "../../service/fetch/auth/SaveFetch";
import { RegisterFetchProps } from "../../props/auth/register/RegisterFetchProps";

let registerService = new SaveFetch();

export class SavePage {
    name = ko.observable("");
    email = ko.observable("");
    subscribe = ko.observable(false);
    role = ko.observable("user");
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

        const result = await registerService.save(payload);

        if (!result) {
            this.errors.push("Не удалось сохранить данные или пользователь уже существует");
            return false;
        }

        this.name("");
        this.email("");
        this.subscribe(false);
        this.role("user");

        if (result) {
            window.location.href = "/#dashboard";
            return false;
        }
        return false;
    }

    render() {
        const root = document.getElementById("root");
        if (!root) return;


        ko.cleanNode(root);

        root.innerHTML = `
        <nav class="navbar navbar-light bg-light shadow-sm mb-4">
            <div class="container">
                <button id="go-dashboard" class="btn btn-primary">Dashboard</button>
            </div>
        </nav>

        <div class="d-flex justify-content-center align-items-center" style="min-height: calc(100vh - 70px);">
            <div class="card shadow p-4" style="width: 100%; max-width: 400px;">
                <h2 class="card-title text-center mb-3">Регистрация</h2>
                <div data-bind="foreach: errors">
                    <p class="text-danger small mb-1" data-bind="text: $data"></p>
                </div>
                <form data-bind="submit: saveData">
                    <div class="mb-3">
                        <input placeholder="Имя" data-bind="value: name" class="form-control"/>
                    </div>
                    <div class="mb-3">
                        <input placeholder="Email" data-bind="value: email" class="form-control"/>
                    </div>
                    <div class="form-check mb-3">
                        <input type="checkbox" data-bind="checked: subscribe" class="form-check-input" id="subscribeCheck"/>
                        <label class="form-check-label" for="subscribeCheck">Подписка</label>
                    </div>
                    <div class="mb-3">
                        <select data-bind="value: role" class="form-select">
                            <option value="user">User</option>
                            <option value="admin">Admin</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Сохранить</button>
                </form>
            </div>
        </div>
        `;

        const dashboardBtn = document.getElementById("go-dashboard");
        if (dashboardBtn) {
            dashboardBtn.addEventListener("click", () => {
                window.location.href = "/#dashboard";
            });
        }
        ko.cleanNode(root);
        ko.applyBindings(this, root);
    }
}