import "./login.scss"
import {useContext, useState} from "react";
import {auth, signIn} from "../../Service/authService";
import {useNavigate} from "react-router-dom";
import {AuthContext} from "../../Context/AuthContext";

function Login(props) {
    let [error, setError] = useState(false)
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const navigate = useNavigate()

    const {dispatch} = useContext(AuthContext)

    const handleLogin = (e) => {
        e.preventDefault()


        signIn(username, password)
            .then(() => {
               dispatch({type: "LOGIN", payload:auth})
               navigate("/")
            },
              error => {
                console.log("Login fail: " + error.toString())
                setError(true)
            })
    }

    return (
        <div className={"login"}>
            <form onSubmit={handleLogin}>
                <input type="text" placeholder={"username"} onChange={(e => setUsername(e.target.value))} value={username}/>
                <input type="password" placeholder={"password"} onChange={(e => setPassword(e.target.value))} value={password}/>
                <button type={"submit"}>Login</button>
                {error && <span>Wrong Username or password!</span>}
            </form>
        </div>
    );
}

export default Login;