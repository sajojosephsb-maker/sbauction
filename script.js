socket.on('userListUpdate', (users) => {
    const tableBody = document.querySelector("table tbody");
    if(tableBody) {
        tableBody.innerHTML = users.map(u => `
            <tr>
                <td>${u.userId} (${u.role})</td>
                <td><span style="color:${u.status === 'active' ? 'green' : 'red'}">${u.status}</span></td>
                <td>0</td>
            </tr>
        `).join('');
    }
});
// Request the list as soon as the page loads
socket.emit('getUsers');
