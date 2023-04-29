const showUsersButton = document.querySelector('#show-users');
const userList = document.querySelector('#user-list');

const uploadForm = document.querySelector('#upload-form');
const fileInput = document.querySelector('#file-input');

const generateEmailsButton = document.querySelector('#generate-emails');

//Get All users
showUsersButton.addEventListener('click', function() {
  const page = 1; // set the page number
  const limit = 10; // set the number of records per page


  //fetch(`http://localhost:8080/students?page=${page}&limit=${limit}`)
  fetch(`http://localhost:8080/students/getAll`)
    .then(response => response.json())
    .then(data => {
      // create the table
      const table = document.createElement('table');

      // create the table headers
      const headers = ['Name', 'Email', 'Amount Remaining'];
      const headerRow = document.createElement('tr');
      headers.forEach(header => {
        const th = document.createElement('th');
        th.textContent = header;
        headerRow.appendChild(th);
      });
      table.appendChild(headerRow);

      // create the table rows
      data.forEach(user => {
        const tr = document.createElement('tr');
        const tdName = document.createElement('td');
        tdName.textContent = user.name;
        tr.appendChild(tdName);
        const tdEmail = document.createElement('td');
        tdEmail.textContent = user.email;
        tr.appendChild(tdEmail);
        const tdAmountRemaining = document.createElement('td');
        tdAmountRemaining.textContent = user.amountRemaining;
        tr.appendChild(tdAmountRemaining);
        table.appendChild(tr);
      });

      // display the table
      userList.innerHTML = '';
      userList.appendChild(table);
    })
    .catch(error => console.error(error));

});

//Upload data using excel
 uploadForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append('file', fileInput.files[0]);
    alert("File Uploaded");
    fetch('http://localhost:8080/students/upload', {
      method: 'POST',
      body: formData
    })
    .then(response => response.json())
    .then(data => {
      console.log('File uploaded successfully:', data);
    })
    .catch(error => console.error('Error uploading file:', error));
  });


  //Generate emails
  generateEmailsButton.addEventListener('click', function() {

  alert("Emails Generated");
    fetch('http://localhost:8080/students/emails')
      .then(response => response.json())
      .then(data => {
        // Do something with the data
        console.log(data);
      })
      .catch(error => console.error(error));
  });