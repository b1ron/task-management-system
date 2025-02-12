(async function createTask() {
    let task = { title: '2', priority: 'P1', completed: false };
    await fetch('http://localhost:8080/tasks/', {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
    })
})();
