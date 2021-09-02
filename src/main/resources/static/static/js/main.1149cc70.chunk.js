(this.webpackJsonpclient=this.webpackJsonpclient||[]).push([[0],{195:function(e,t,n){},196:function(e,t,n){},433:function(e,t,n){"use strict";n.r(t);var a=n(1),c=n.n(a),s=n(100),l=n.n(s),i=(n(195),n(196),n(129)),r=n(15),o=n(35),d=n(36),h=n(30),j=n(41),m=n(40),b=n(54),p=n.n(b),u=n(435);Object(u.a)();var O="http://localhost:8089/api/employees",y=new(function(){function e(){Object(o.a)(this,e)}return Object(d.a)(e,[{key:"getEmployee",value:function(){return p.a.get(O)}},{key:"saveEmployee",value:function(e){return p.a.post(O,e)}},{key:"updateEmployee",value:function(e,t){return p.a.put(O+"/"+t,e)}},{key:"getEmployeeById",value:function(e){return p.a.post(O+"/"+e)}},{key:"getEmployeeByCode",value:function(e){return p.a.post(O+"/getByCode/"+e)}},{key:"deleteEmployee",value:function(e){return p.a.delete(O+"/"+e)}},{key:"checkCode",value:function(e,t){var n={params:{id:e,code:t}};return p.a.get("http://localhost:8089/api/employees/checkCode",n)}}]),e}()),x=n(2),v=function(e){Object(j.a)(n,e);var t=Object(m.a)(n);function n(e){var a;return Object(o.a)(this,n),(a=t.call(this,e)).state={employees:[]},a.addEmployee=a.addEmployee.bind(Object(h.a)(a)),a.editEmployee=a.editEmployee.bind(Object(h.a)(a)),a.deleteEmployee=a.deleteEmployee.bind(Object(h.a)(a)),a}return Object(d.a)(n,[{key:"addEmployee",value:function(){this.props.history.push("/add-employee/ ")}},{key:"editEmployee",value:function(e){this.props.history.push("/add-employee/".concat(e))}},{key:"deleteEmployee",value:function(e){var t=this;y.deleteEmployee(e).then((function(n){t.setState({employees:t.state.employees.filter((function(t){return t.id!==e}))})}))}},{key:"viewEmployee",value:function(e){this.props.history.push("/view-employee/".concat(e))}},{key:"componentDidMount",value:function(){var e=this;y.getEmployee().then((function(t){e.setState({employees:t.data})}))}},{key:"render",value:function(){var e=this;return Object(x.jsxs)("div",{children:[Object(x.jsx)("h2",{className:"text-center",children:"Employee List"}),Object(x.jsx)("div",{className:"",children:Object(x.jsx)("button",{className:"btn btn-primary",onClick:this.addEmployee,children:" Add Employee"})}),Object(x.jsx)("br",{}),Object(x.jsx)("div",{className:"row",children:Object(x.jsxs)("table",{className:"table table-striped table-bordered",children:[Object(x.jsx)("thead",{children:Object(x.jsxs)("tr",{children:[Object(x.jsx)("td",{children:" Code "}),Object(x.jsx)("td",{children:" Name "}),Object(x.jsx)("td",{children:" Age "}),Object(x.jsx)("td",{children:" Email "}),Object(x.jsx)("td",{children:" Phone "}),Object(x.jsx)("td",{children:" Actions "})]})}),Object(x.jsx)("tbody",{children:this.state.employees.map((function(t){return Object(x.jsxs)("tr",{children:[Object(x.jsx)("td",{children:t.code}),Object(x.jsx)("td",{children:t.name}),Object(x.jsx)("td",{children:t.age}),Object(x.jsx)("td",{children:t.email}),Object(x.jsx)("td",{children:t.phone}),Object(x.jsxs)("td",{children:[Object(x.jsx)("button",{onClick:function(){return e.editEmployee(t.id)},className:"btn btn-info",children:"Update "}),Object(x.jsx)("button",{style:{marginLeft:"10px"},onClick:function(){return e.deleteEmployee(t.id)},className:"btn btn-danger",children:"Delete "}),Object(x.jsx)("button",{style:{marginLeft:"10px"},onClick:function(){return e.viewEmployee(t.id)},className:"btn btn-info",children:"View "})]})]},t.id)}))})]})})]})}}]),n}(a.Component),g=function(e){Object(j.a)(n,e);var t=Object(m.a)(n);function n(){return Object(o.a)(this,n),t.apply(this,arguments)}return Object(d.a)(n,[{key:"render",value:function(){return Object(x.jsx)("div",{children:Object(x.jsx)("footer",{className:"footer",children:Object(x.jsx)("span",{className:"text-muted",children:"All Rights Reserved 2021 @hieu"})})})}}]),n}(a.Component),f=function(e){Object(j.a)(n,e);var t=Object(m.a)(n);function n(e){var a;return Object(o.a)(this,n),(a=t.call(this,e)).state={employees:[]},a}return Object(d.a)(n,[{key:"render",value:function(){return Object(x.jsx)("div",{children:Object(x.jsx)("header",{children:Object(x.jsx)("nav",{className:"navbar navbar-expand-md navbar-dark bg-dark",children:Object(x.jsx)("div",{children:Object(x.jsx)("a",{href:"",className:"navbar-brand",children:"Employee Management"})})})})})}}]),n}(a.Component),N=n(80);n(216);N.a.configure({autoClose:2e3,draggable:!1,limit:3});var E=function(e){Object(j.a)(n,e);var t=Object(m.a)(n);function n(e){var a;return Object(o.a)(this,n),(a=t.call(this,e)).saveOrUpdate=function(e){e.preventDefault();var t={name:a.state.name,code:a.state.code,email:a.state.email,age:a.state.age,phone:a.state.phone};console.log("employee => "+JSON.stringify(t)),y.checkCode(a.state.id,t.code).then((function(e){e.data?N.a.error("M\xe3 \u0111\xe3 \u0111\u01b0\u1ee3c s\u1eed d\u1ee5ng!"):" "===a.state.id?(0===a.state.name.length&&alert("a"),y.saveEmployee(t).then((function(e){a.props.history.push("/employees"),N.a.success("Th\xeam th\xe0nh c\xf4ng!")}))):y.updateEmployee(t,a.state.id).then((function(e){a.props.history.push("/employees"),N.a.success("C\u1eadp nh\u1eadt th\xe0nh c\xf4ng!")}))}))},a.changeNameHandler=function(e){null===e.target&&alert("a"),a.setState({name:e.target.value})},a.changeCodeHandler=function(e){a.setState({code:e.target.value})},a.changeEmailHandler=function(e){a.setState({email:e.target.value})},a.changePhoneHandler=function(e){a.setState({phone:e.target.value})},a.changeAgeHandler=function(e){a.setState({age:e.target.value})},a.state={id:a.props.match.params.id,name:"",code:"",email:"",age:"",phone:""},a.changeCodeHandler=a.changeCodeHandler.bind(Object(h.a)(a)),a.changeNameHandler=a.changeNameHandler.bind(Object(h.a)(a)),a.changeEmailHandler=a.changeEmailHandler.bind(Object(h.a)(a)),a.changePhoneHandler=a.changePhoneHandler.bind(Object(h.a)(a)),a.changeAgeHandler=a.changeAgeHandler.bind(Object(h.a)(a)),a.saveOrUpdate=a.saveOrUpdate.bind(Object(h.a)(a)),a}return Object(d.a)(n,[{key:"componentDidMount",value:function(){var e=this;"\\s+"===this.state.id||y.getEmployeeById(this.state.id).then((function(t){var n=t.data;e.setState({code:n.code,name:n.name,email:n.email,age:n.age,phone:n.phone})}))}},{key:"cancel",value:function(){this.props.history.push("/employees")}},{key:"render",value:function(){return Object(x.jsx)("div",{children:Object(x.jsx)("div",{className:"container",children:Object(x.jsx)("div",{className:"row",children:Object(x.jsxs)("div",{className:"card col-md-6 offset-md-3 offset-md-3",children:[Object(x.jsx)("h3",{className:"text-center",children:"Add Employee"}),Object(x.jsx)("div",{className:"card-body",children:Object(x.jsxs)("form",{action:"",children:[Object(x.jsxs)("div",{className:"form-group",children:[Object(x.jsxs)("label",{children:["Name ",Object(x.jsx)("span",{className:"text-danger",children:"*"})," "]}),Object(x.jsx)("input",{placeholder:"Name",name:"name",className:"form-control",value:this.state.name,onChange:this.changeNameHandler,required:!0})]}),Object(x.jsxs)("div",{className:"form-group",children:[Object(x.jsxs)("label",{children:["Code ",Object(x.jsx)("span",{className:"text-danger",children:"*"})," "]}),Object(x.jsx)("input",{type:"email",placeholder:"Code",name:"code",className:"form-control",value:this.state.code,onChange:this.changeCodeHandler})]}),Object(x.jsxs)("div",{className:"form-group",children:[Object(x.jsxs)("label",{children:["Email ",Object(x.jsx)("span",{className:"text-danger",children:"*"})," "]}),Object(x.jsx)("input",{type:"email",placeholder:"Email",name:"email",className:"form-control",value:this.state.email,onChange:this.changeEmailHandler})]}),Object(x.jsxs)("div",{className:"form-group",children:[Object(x.jsxs)("label",{children:["Age ",Object(x.jsx)("span",{className:"text-danger",children:"*"})," "]}),Object(x.jsx)("input",{placeholder:"Age",name:"age",className:"form-control",value:this.state.age,onChange:this.changeAgeHandler})]}),Object(x.jsxs)("div",{className:"form-group",children:[Object(x.jsxs)("label",{children:["Phone ",Object(x.jsx)("span",{className:"text-danger",children:"*"})," "]}),Object(x.jsx)("input",{placeholder:"Phone",name:"phone",className:"form-control",value:this.state.phone,onChange:this.changePhoneHandler})]}),Object(x.jsx)("button",{className:"btn btn-success",onClick:this.saveOrUpdate,style:{marginLeft:"10px"},children:"Save"}),Object(x.jsx)("button",{className:"btn btn-danger",onClick:this.cancel.bind(this),style:{marginLeft:"10px"},children:"Cancel"})]})})]})})})})}}]),n}(a.Component),C=function(e){Object(j.a)(n,e);var t=Object(m.a)(n);function n(e){var a;return Object(o.a)(this,n),(a=t.call(this,e)).state={id:a.props.match.params.id,employee:{}},a}return Object(d.a)(n,[{key:"componentDidMount",value:function(){var e=this;y.getEmployeeById(this.state.id).then((function(t){e.setState({employee:t.data})}))}},{key:"cancel",value:function(){this.props.history.push("/employees")}},{key:"render",value:function(){return Object(x.jsxs)("div",{children:[Object(x.jsx)("br",{}),Object(x.jsxs)("div",{className:"card col-md-6 offset-md-3",children:[Object(x.jsx)("h3",{className:"text-center",children:" View Employee Details"}),Object(x.jsxs)("div",{className:"card-body",children:[Object(x.jsx)("div",{className:"row",children:Object(x.jsxs)("label",{children:[" Employee Code: ",this.state.employee.code]})}),Object(x.jsx)("div",{className:"row",children:Object(x.jsxs)("label",{children:[" Employee Name: ",this.state.employee.name]})}),Object(x.jsx)("div",{className:"row",children:Object(x.jsxs)("label",{children:[" Employee Email: ",this.state.employee.email]})}),Object(x.jsx)("div",{className:"row",children:Object(x.jsxs)("label",{children:[" Employee Age: ",this.state.employee.age]})}),Object(x.jsx)("div",{className:"row",children:Object(x.jsxs)("label",{children:[" Employee Phone: ",this.state.employee.phone]})}),Object(x.jsx)("button",{className:"btn btn-danger",onClick:this.cancel.bind(this),style:{marginLeft:"10px"},children:"Cancel"})]})]})]})}}]),n}(a.Component);n(217);var k=function(){return Object(x.jsx)("div",{children:Object(x.jsxs)(i.a,{children:[Object(x.jsx)(f,{}),Object(x.jsx)("div",{className:"container",children:Object(x.jsxs)(r.c,{children:[Object(x.jsx)(r.a,{path:"/",exact:!0,component:v}),Object(x.jsx)(r.a,{path:"/employees",component:v}),Object(x.jsx)(r.a,{path:"/add-employee/:id",component:E}),Object(x.jsx)(r.a,{path:"/view-employee/:id",component:C})]})}),Object(x.jsx)(g,{})]})})},H=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,436)).then((function(t){var n=t.getCLS,a=t.getFID,c=t.getFCP,s=t.getLCP,l=t.getTTFB;n(e),a(e),c(e),s(e),l(e)}))};l.a.render(Object(x.jsx)(c.a.StrictMode,{children:Object(x.jsx)(k,{})}),document.getElementById("root")),H()}},[[433,1,2]]]);
//# sourceMappingURL=main.1149cc70.chunk.js.map