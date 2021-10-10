import React, {Component} from 'react';
import EmployeeService from "../../services/EmployeeService";
import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

toast.configure({
    autoClose: 2000,
    draggable: false,
    limit: 3
    //etc you get the idea
});

//
// function FormError(props) {
//     /* nếu isHidden = true, return null ngay từ đầu */
//     if (props.state.name.length === 0) {
//         return null;
//     }
//
//     return (<div style={{marginBottom: "10px"}}>{props.errorMessage}</div>)
// }

class CreateEmployee extends Component {
    constructor(props) {
        super(props);

        this.state = {

            id: this.props.match.params.id,
            name: '',
            code: '',
            email: '',
            age: '',
            phone: ''


        }
        this.changeCodeHandler = this.changeCodeHandler.bind(this);
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePhoneHandler = this.changePhoneHandler.bind(this);
        this.changeAgeHandler = this.changeAgeHandler.bind(this);
        this.saveOrUpdate = this.saveOrUpdate.bind(this);

    }


    componentDidMount() {
        if (this.state.id === '\\s+') {

        } else {
            EmployeeService.getEmployeeById(this.state.id).then((res) => {
                let employee = res.data
                this.setState({

                    code: employee.code,
                    name: employee.name,
                    email: employee.email,
                    age: employee.age,
                    phone: employee.phone
                })
            })
        }

    }


    saveOrUpdate = (e) => {
        e.preventDefault();

        // let {match: {params}} = this.props;
        //
        // let {id} = params;

        // console.log(id)
        let employee = {
            name: this.state.name,
            code: this.state.code,
            email: this.state.email,
            age: this.state.age,
            phone: this.state.phone,
        }
        console.log('employee => ' + JSON.stringify(employee));

        EmployeeService.checkCode(this.state.id, employee.code).then((res) => {
            if (res.data) {
                toast.error('Mã đã được sử dụng!')
            } else {
                if (this.state.id === '') {

                    EmployeeService.saveEmployee(employee).then((res) => {
                        this.props.history.push("/employees")
                        toast.success('Thêm thành công!')
                    })
                } else {
                    EmployeeService.updateEmployee(employee, this.state.id).then((res) => {
                        this.props.history.push("/employees")
                        toast.success('Cập nhật thành công!')
                    })
                }
            }

        })


    }
    changeNameHandler = (event) => {
        if (event.target === null) alert("a")
        this.setState({name: event.target.value});
    }
    changeCodeHandler = (event) => {
        this.setState({code: event.target.value});
    }
    changeEmailHandler = (event) => {
        this.setState({email: event.target.value});
    }
    changePhoneHandler = (event) => {
        this.setState({phone: event.target.value});
    }
    changeAgeHandler = (event) => {
        this.setState({age: event.target.value});
    }

    cancel() {
        this.props.history.push('/employees');
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Employee</h3>
                            <div className="card-body">
                                <form action="">
                                    <div className="form-group">
                                        <label>Name <span className={"text-danger"}>*</span> </label>
                                        <input placeholder={"Name"} name={"name"} className={"form-control"}
                                               value={this.state.name} onChange={this.changeNameHandler}
                                               required={true}/>

                                    </div>
                                    <div className="form-group">
                                        <label>Code <span className={"text-danger"}>*</span> </label>
                                        <input type={"email"} placeholder={"Code"} name={"code"}
                                               className={"form-control"}
                                               value={this.state.code} onChange={this.changeCodeHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <label>Email <span className={"text-danger"}>*</span> </label>
                                        <input type={"email"} placeholder={"Email"} name={"email"}
                                               className={"form-control"}
                                               value={this.state.email} onChange={this.changeEmailHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Age <span className={"text-danger"}>*</span> </label>
                                        <input placeholder={"Age"} name={"age"} className={"form-control"}
                                               value={this.state.age} onChange={this.changeAgeHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Phone <span className={"text-danger"}>*</span> </label>
                                        <input placeholder={"Phone"} name={"phone"} className={"form-control"}
                                               value={this.state.phone} onChange={this.changePhoneHandler}/>
                                    </div>

                                    <button className={"btn btn-success"} onClick={this.saveOrUpdate}
                                            style={{marginLeft: "10px"}}>Save
                                    </button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        );
    }
}

export default CreateEmployee;