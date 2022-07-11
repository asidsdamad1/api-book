import axios from "axios";

const AuthenticationService = () => {
    return JSON.parse(localStorage.getItem("user"));
}

const signin = (username, password) => {
    return axios.post("http://localhost:8091/api/auth/signin", {username, password})
        .then(response => {
            if (response.data.accessToken) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }
            return response.data;
        })
        .then(result => {
            console.log(result)
            localStorage.setItem("accessToken", result.accessToken);
        })
        .catch(err => {
            console.log(err);
            throw err;
        });
}

const signOut = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("accessToken");
}
const register1 = async(username, email, password) => {
    return axios.post("http://localhost:8091/public/user/", {
        username,
        email,
        password
    });
}

export const auth = AuthenticationService;
export const signIn = signin;
export const register = register1;
export const logout = signOut;