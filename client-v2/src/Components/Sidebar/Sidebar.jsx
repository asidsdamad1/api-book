import "./sidebar.scss"
import DashboardIcon from '@mui/icons-material/Dashboard';
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import StoreIcon from '@mui/icons-material/Store';
import LocalShippingIcon from '@mui/icons-material/LocalShipping';
import QueryStatsIcon from '@mui/icons-material/QueryStats';
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone';
import SettingsSystemDaydreamIcon from '@mui/icons-material/SettingsSystemDaydream';
import SettingsApplicationsRoundedIcon from '@mui/icons-material/SettingsApplicationsRounded';
import AccountCircleOutlinedIcon from '@mui/icons-material/AccountCircleOutlined';
import LogoutIcon from '@mui/icons-material/Logout';
import CreditCardIcon from '@mui/icons-material/CreditCard';
import {Link} from "react-router-dom"
import {useContext} from "react";
import {DarkModeContext} from "../../Context/darkModeContext";
import {AuthContext} from "../../Context/AuthContext";
import {logout} from "../../Service/authService";

function Sidebar(props) {
    const {dispatchDarkMode} = useContext(DarkModeContext)
    const {dispatch} = useContext(AuthContext)

    const signOut = () => {
        logout()
        dispatch({type: "LOGOUT"})
    }
    return (
        <div className={"sidebar"}>
            <div className="top">
                <Link to={"/"} style={{textDecoration:"none"}} >
                    <span className="logo">Admin</span>
                </Link>
            </div>
            <hr/>
            <div className="center">
                <ul>
                    <p className="title">MAIN</p>
                    <Link to={"/"} style={{textDecoration:"none"}} >
                        <li>
                            <DashboardIcon className={"icon"}/>
                            <span>Dashboard</span>
                        </li>
                    </Link>
                    <p className="title">LIST</p>
                    <Link to={"/users"} style={{textDecoration:"none"}} >
                        <li>
                            <PersonOutlineOutlinedIcon className={"icon"}/>
                            <span>Users</span>
                        </li>
                    </Link>
                    <Link to={"/products"} style={{textDecoration:"none"}} >
                    <li>
                        <StoreIcon className={"icon"}/>
                        <span>Products</span>
                    </li>
                    </Link>

                    <li>
                        <CreditCardIcon className={"icon"}/>
                        <span>Orders</span>
                    </li>
                    <li>
                        <LocalShippingIcon className={"icon"}/>
                        <span>Delivery</span>
                    </li>
                    <p className="title">USERFUL</p>
                    <li>
                        <QueryStatsIcon className={"icon"}/>
                        <span>Stats</span>
                    </li>
                    <li>
                        <NotificationsNoneIcon className={"icon"}/>
                        <span>Notifications</span>
                    </li>
                    <p className="title">SERVICE</p>
                    <li>
                        <SettingsSystemDaydreamIcon className={"icon"}/>
                        <span>System</span>
                    </li>
                    <li>
                        <SettingsApplicationsRoundedIcon className={"icon"}/>
                        <span>Settings</span>
                    </li>
                    <p className="title">USER</p>
                    <li>
                        <AccountCircleOutlinedIcon className={"icon"}/>
                        <span>Profile</span>
                    </li>
                    <li>
                        <LogoutIcon className={"icon"}/>
                        <span onClick={signOut}>Logout</span>
                    </li>
                </ul>
            </div>
            <div className="bottom">
                <div
                    className="colorOption"
                    onClick={() => dispatchDarkMode({type: "LIGHT"})}></div>
                <div
                    className="colorOption"
                    onClick={() => dispatchDarkMode({type: "DARK"})}></div>
            </div>
        </div>

    );
}

export default Sidebar;