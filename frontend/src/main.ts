import { Router } from "./service/router/Router";
import { RegisterPage } from "./pages/register/RegisterPage";
import { AuthCheckFetch } from "./service/fetch/check/CheckAuthFetch"
import {DashboardPage} from "./pages/dashboard/DashboardPage";

const auth = new AuthCheckFetch();
const router = new Router();
let isAuth = auth.checkAuth();

if(!isAuth){
    router.register("register", RegisterPage);
}else{
    router.register("dashboard", DashboardPage);
}


