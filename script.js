socket.on('userListUpdate', (users) => {
    const companyBody = document.getElementById('company-summary-body');
    // Filter out only the users with the 'company' role
    const companies = users.filter(u => u.role === 'company');
    
    companyBody.innerHTML = companies.map(c => `
        <tr>
            <td>${c.userId}</td>
            <td><span style="color:${c.status === 'active' ? 'green' : 'red'}">${c.status}</span></td>
            <td>Checking...</td> 
        </tr>
    `).join('');
});
