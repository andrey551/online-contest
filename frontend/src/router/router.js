import { createBrowserRouter } from "react-router-dom";
import Courses from "../pages/common/courses";
import SubmitBoard from "../pages/common/submit-board";
import CreateCourse from "../pages/Teacher/create-laboratory";
import User from "../api/user";
import LaboratoriesPage from "../pages/common/lab/labPage";
import ErrorPage from "../pages/common/error/error";

function ControlPanel() {
    return null;
}

const router = createBrowserRouter([
    {
        path: "/",
        element: <Courses/>
    },
    {
        path: "/laboratory",
        element: <LaboratoriesPage/>
    },
    {
        path: "/submit-board",
        element: <SubmitBoard/>
    },
    {
        path: "/user",
        element: <User/>
    },
    {
        path: "/teacher/control-panel",
        element: <ControlPanel/>

    },
    {
        path: "/teacher/create/laboratory",
        element: <CreateCourse/>
    },
    {
        path: '*',
        element: <ErrorPage errorCode={404} errorMessage="Page Not Found" />,
    }
])

export default router;