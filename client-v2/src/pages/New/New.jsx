import "./new.scss"
import Sidebar from "../../Components/Sidebar/Sidebar";
import Navbar from "../../Components/Navbar/Navbar";
import DriveFolderUploadOutlinedIcon  from "@mui/icons-material/DriveFolderUploadOutlined"
import {useState} from "react";
import {SaveEmployee, UpdateEmployee} from "../../Service/userService";
import {useNavigate, useParams} from "react-router-dom";

function New({inputs, title}) {
    const [file, setFile] = useState("");
    const [data, setData] = useState({})
    const [employee, setEmployee] = useState({})

    const navigate = useNavigate();
    const handleInput = (e) => {
        const id = e.target.id
        const value = e.target.value

        setData({...data, [id]: value })
    }



    const handleAdd = (e) => {
        e.preventDefault()
        try {
            setEmployee(data)
                SaveEmployee(employee).then((res) => {
                    navigate("/users")
                })


        }catch (err) {
            console.log(err)
        }
    }
    return (
        <div className={"new"}>
            <Sidebar />
            <div className="newContainer">
                <Navbar />
                <div className="top">
                    <h1>{title}</h1>
                </div>
                <div className="bottom">
                    <div className="left">
                        <img src={
                                file
                                    ? URL.createObjectURL(file)
                                    :"https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg"}
                             alt=""/>
                    </div>
                    <div className="right">
                        <form onSubmit={handleAdd}>
                            <div className="formInput">
                                <label htmlFor={"file"}>Image: <DriveFolderUploadOutlinedIcon className={"icon"}/></label>
                                <input
                                    type="file"
                                    id={"file"}
                                    onChange={(e) => setFile(e.target.files[0])}
                                    style={{display: "none"}}/>
                            </div>
                            {inputs.map(input => (
                                <div className={"formInput"} key={input.id}>
                                    <label htmlFor="">{input.label}</label>
                                    <input
                                        id={input.id}
                                        type={input.type} placeholder={input.placeholder} onChange={handleInput}/>
                                </div>
                            ))}

                            <button type={"submit"}>Send</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default New;