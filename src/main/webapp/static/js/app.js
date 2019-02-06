
var nextItemNum = 3;	// For addToList, see later
var nextEeElt = 1;		// For fetchEmpList / add Employee LI to empList UL

sayHi = function() {
	alert('Hello!');
}

sayHi2 = function() {
	document.getElementById('message').innerHTML = "Hello";
}

sayBye2 = function(greeting) {
	var s = document.getElementById('userName').value;
	document.getElementById('message').innerHTML = greeting+ " " + s;
}


/*
 * ====== Functions for INPUT... panels ======
 */
showMsg = function(msg) {
	document.getElementById('result').innerHTML = msg;
}

showPanel = function(panel) {
	if (panel === 'all') {
		showAllPanels();
	}
	else {
		hideAllPanels();
		if (panel != 'none') {
			setVisible(true, panel+"-panel");
		}
	}
}

setVisible = function(show, ele) {
	if (show) {		
		document.getElementById(ele).style.display = "block";
	}
	else {
		document.getElementById(ele).style.display = "none";
	}
}
showAllPanels = function() {
	setVisible(true, "input-panel");
	setVisible(true, "rest-panel");
	setVisible(true, "list-panel");
	setVisible(true, "pathParam-panel");
	setVisible(true, "rcvJson-panel");
	setVisible(true, "edit-panel");
	setVisible(true, "restList-panel");
}
hideAllPanels = function() {
	setVisible(false, "input-panel");
	setVisible(false, "rest-panel");
	setVisible(false, "list-panel");
	setVisible(false, "pathParam-panel");
	setVisible(false, "rcvJson-panel");
	setVisible(false, "edit-panel");
	setVisible(false, "restList-panel");
}


//Demo of Inputting text
greetUser = function() {
	var s = document.getElementById('userName').value;
	document.getElementById('message').innerHTML = "Hello " + s;
}

//rest1, rest2 = Demos of calling REST interfaces
items = function() {
	const Http = new XMLHttpRequest();
	const url= 'http:rest/items';	// Use relative URL (ie within this site), dont hard-code like 'http://localhost:7070/';
	Http.open("GET", url);
	Http.send();
	Http.onreadystatechange=(e) => {
		showMsg(Http.responseText);
	}
}

rest2 = function() {
	var msgNum = document.getElementById('msgNum').value;
	const Http = new XMLHttpRequest();
	const url= 'http:rest/msg' + msgNum;
	try {
		Http.open("GET", url);
		Http.send();
	}
	catch (err) { // "No such URL" Exception not shown, but demonstrates JS exception handling:
		showMsg("ERROR in GET: " + url + " : " + err.message);
	}
	Http.onreadystatechange=(e) => {
		// Could check Http.status here, see later examples below
		showMsg(Http.responseText);
	}
}


//Demo of dynamically adding items to a list on the page, eg to display a list of employees from REST server
addToList = function() {
	var ul = document.getElementById("dynamic-list");
	var li = document.createElement("li");
	li.appendChild(document.createTextNode(nextItemNum * 111));
	li.setAttribute("id", "element" + nextItemNum++);
	ul.appendChild(li);
}


//Demo of using a single REST handler for many different resources / requests
restPathParam = function() {
	var eeNum= document.getElementById('eeNum').value;
	const Http = new XMLHttpRequest();
	const url= 'http:rest/employee/' + eeNum + '/name';
	document.getElementById('eePathUri').innerHTML = 'GET &nbsp;' + url;
	Http.open("GET", url);
	Http.send();
	Http.onreadystatechange=(e) => {
		document.getElementById('eeName').innerHTML = Http.responseText;
	}
}


//Demo of receiving data from REST interface - AS A JSON OBJECT
getAsJson = function() {
	var eeNum= document.getElementById('eeNum2').value;
	const Http = new XMLHttpRequest();
	const url= 'http:rest/employee/' + eeNum;
	document.getElementById('eeJsonUri').innerHTML = 'GET &nbsp;' + url;
	Http.open("GET", url);
	Http.send();
	Http.onreadystatechange=(e) => {
		if (Http.readyState == 4) {
			if (Http.status == 200) {
				var ee = JSON.parse(Http.responseText);
				console.log("name= "+ ee.name);
				document.getElementById('ee2Name').innerHTML = ee.name;
				document.getElementById('ee2Office').innerHTML = ee.office;
				document.getElementById('ee2Salary').innerHTML = ee.salary;
				showMsg("GET succeeded")
			}
			else {
				showMsg( 'ERROR: ' + Http.status + ' ('+Http.statusText+')' );
			}
		}
	}
}


//Demo of writing JSON to REST interface (using POST, PUT http verbs)
//First some utility functions for the `editXyz` functions of this demo:
displayEee3 = function(ee) {
	document.getElementById('ee3Id').value = ''+ ee.id; // For POST/create we need to show ID of newly created Employee
	document.getElementById('ee3Name').value = ee.name;
	document.getElementById('ee3Office').value = ee.office;
	document.getElementById('ee3Salary').value = ee.salary;
}

editHttpStateChangeHandler = function(e) {
	var Http = e.currentTarget;
	if (Http.readyState == 4) {
		if (Http.status == 200) {
			displayEee3(JSON.parse(Http.responseText));	// Display the returned Employee onto ee3* fields
			showMsg('http request succeeded'); // Cant determine which http verb from e or Http
		}
		else {
			showMsg( 'ERROR: ' + Http.status + ' ('+Http.statusText+')' );
		}
	}
}

//Constructor function
function Employee (id, name, office, salary) { 
	this.id = id;
	this.name = name;
	this.office = office;
	this.salary = salary;
}

createEmployee_ee3 = function() {
	var eeNum = document.getElementById('ee3Id').value;
	if (eeNum == '') {
		eeNum= 0;	// POST (and others?) need an int here, for when Jackson creates Employee DTO to pass into REST handler
	}
	var name = document.getElementById('ee3Name').value;
	var office = document.getElementById('ee3Office').value;
	var salary = document.getElementById('ee3Salary').value;
	var emp = new Employee( eeNum, name, office, salary );
	return emp;
}

//Read JSON. As per earlier `getAsJson` demo, but factored out the readystatechange handler and object display
editGetJson = function() {
	var eeNum= document.getElementById('ee3Id').value;
	const url= 'http:rest/employee/' + eeNum;
	const Http = new XMLHttpRequest();
	document.getElementById('eeEditUri').innerHTML = 'GET &nbsp;' + url;
	Http.open("GET", url);
	Http.send();
	Http.onreadystatechange = editHttpStateChangeHandler;
}

//Demo of PUT (update an Employee)
editUpdateEmp= function() {
	var emp = createEmployee_ee3();	// Create from emp3* fields
	const url= 'http:rest/employee/';
	const Http = new XMLHttpRequest();
	document.getElementById('eeEditUri').innerHTML = 'PUT &nbsp;' + url;
	Http.onerror=(e) => { // Not used, see onreadystatechange below instead, but leave this here as an example
		console.log(e);
	}
	Http.open("PUT", url);
	Http.setRequestHeader("Content-Type", "application/json");
	Http.send( JSON.stringify(emp) );
	Http.onreadystatechange = editHttpStateChangeHandler;
}

//Demo of POST (create an Employee)
editCreateEmp = function() {
	var emp = createEmployee_ee3();	// Create from emp3* fields
	const url= 'http:rest/employee/';
	const Http = new XMLHttpRequest();
	document.getElementById('eeEditUri').innerHTML = 'POST &nbsp;' + url;
	Http.open("POST", url, true);
	Http.setRequestHeader("Content-Type", "application/json");
	Http.send( JSON.stringify(emp) );
	Http.onreadystatechange = editHttpStateChangeHandler;
}

//Demo of fetching a List of Employees
fetchEmpList = function() {
	var emp = createEmployee_ee3();	// Create from emp3* fields
	const url= 'http:rest/employee/list';
	const Http = new XMLHttpRequest();
	document.getElementById('eeEditUri').innerHTML = 'GET &nbsp;' + url;
	Http.open("GET", url);
	Http.setRequestHeader("Content-Type", "application/json");
	Http.send( );
	Http.onreadystatechange=(e) => {
		if (Http.readyState == 4) {
			if (Http.status == 200) {
				var eeList = JSON.parse(Http.responseText);
				nextEeElt = 1;
				var ul = document.getElementById("ee-list");
				while (ul.firstChild) {
				    ul.removeChild(ul.firstChild);
				}
				for (var i = 0; i < eeList.length; i++) {
					var ee= eeList[i];
					var eeDetails = "Employee "+ ee.id + ": "+ ee.name + ", "+ ee.office + ", salary= "+ ee.salary;
					var li = document.createElement("li");
					li.appendChild(document.createTextNode(eeDetails));
					li.setAttribute("id", "ee-element" + nextEeElt++);
					ul.appendChild(li);
				}
				showMsg("GET succeeded")
			}
			else {
				showMsg( 'ERROR: ' + Http.status + ' ('+Http.statusText+')' );
			}
		}
	}
}
