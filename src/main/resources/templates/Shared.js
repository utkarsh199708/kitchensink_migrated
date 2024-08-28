async function fetchUserList() {
    const token = localStorage.getItem('token');
    if (token) {
        const response = await fetch('/members', {
            headers: {
                'Authorization': token
            }
        });

        if (response.ok) {
            const userList = await response.json();
            renderUserList(userList);
        } else if (response.status === 401) {
            alert('Unauthorized. Please log in again.');
            window.location.href = '/login.html'; // Redirect to login page
        } else {
            alert('Error fetching user list: ' + response.statusText);
        }
    } else {
        alert('No token found. Please log in again.');
        window.location.href = '/login.html'; // Redirect to login page
    }
}

function renderUserList(users) {
    const table = $('#user-table').DataTable();
    table.clear();
    users.forEach(user => {
        table.row.add([
            user.name,
            user.email,

        ]).draw();
    });
}