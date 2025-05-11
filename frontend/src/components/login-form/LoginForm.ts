import React, {useState} from "react";

import "./styles.css";
const LoginForm = () => {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const submitOnAction = () => {
    //     call api request through axios
    }
    return(
        <>
            <div className={"login-form-container"}>
                <div className={"login-header"}>
                    <h1 className={"title"}>LOGIN</h1>
                </div>
                <div className={"login-input-filed"}>
                    <table className={"table-input"}>
                        <tbody>
                        <tr>
                            <td className={"input-field"}>Username</td>
                            <td>
                                <input
                                    required
                                    id = {"username-input"}
                                    value={username}
                                    type={"text"}
                                    onChange={(e) => setUsername(e.target.value)}/>
                            </td>
                        </tr>
                        <tr>
                            <td className={"input-field"}>Password</td>
                            <td>
                                <input
                                    required
                                    value = {password}
                                    type={"password"}
                                    onChange={(e) => setPassword(e.target.value)}/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div className={"submit-bnt-container"}>
                    <button id={"submit-login-button"} onClick={submitOnAction}>Go</button>
                </div>
            </div>
        </>
    )
}
export default LoginForm;