(async function createTask() {
    let task = { title: 'some low priority issue', priority: 'P4', completed: false };
    await fetch('http://localhost:8080/tasks/', {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(task),
    })
})();

(async function deleteTask(id) {
    await fetch('http://localhost:8080/tasks/' + id, {
        method: 'DELETE',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
    })
})(5); // 404

(async function deleteTask(id) {
    await fetch('http://localhost:8080/tasks/' + id, {
        method: 'DELETE',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
    })
})(2); // 200
