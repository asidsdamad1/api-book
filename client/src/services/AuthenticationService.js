import axios from "axios";

class AuthenticationService {
    signin = (username, password) => {
        return axios.post("/api/auth/signin", {username, password})
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

    signOut() {
        localStorage.removeItem("user");
    }

    register = async(firstname, lastname, username, email, password) => {
        return axios.post("/api/auth/signup", {
            username,
            email,
            password
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));;
    }
}

export default new AuthenticationService();