import { Router } from "./service/router/Router";
import { SavePage } from "./pages/register/SavePage";
import {DashboardPage} from "./pages/dashboard/DashboardPage";


const router = new Router();


router.register("/", SavePage);
router.register("dashboard", DashboardPage);



