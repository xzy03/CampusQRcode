function updateTime() {
    const timeElement = document.getElementById('time');
    const now = new Date();
    const formattedTime = now.getFullYear() + '-' +
        String(now.getMonth() + 1).padStart(2, '0') + '-' +
        String(now.getDate()).padStart(2, '0') + ' ' +
        String(now.getHours()).padStart(2, '0') + ':' +
        String(now.getMinutes()).padStart(2, '0') + ':' +
        String(now.getSeconds()).padStart(2, '0');
    timeElement.textContent = formattedTime;
}

// Update time on page load
window.onload = updateTime;

// Update time every second
setInterval(updateTime, 1000);
