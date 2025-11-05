const bubbleColors = ['#e0f7ff', '#ffe8f5', '#f3e8ff', '#e6fff0', '#ffe6eb', '#fffde6', '#fff2e0'];


function login(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (!username) {
        alert('请输入账号');
        return;
    }
    if (!password) {
        alert('请输入密码');
        return;
    }

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
    })
        .then(response => response.json())
        .then(result => {
            if (result.status === 'success') {
                localStorage.setItem('isLoggedIn', 'true');
                localStorage.setItem('user_name', result.user_name);
                console.log('写入 localStorage.signature:', result.signature);
                localStorage.setItem('signature', result.signature);
                alert('登录成功！即将跳转到次元舱...');
                window.location.href = 'index.html';
            } else {
                alert(result.message || '登录失败，请检查账号密码');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('登录过程中发生错误，请稍后再试。');
        });
}



function register(event) {
    event.preventDefault();
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const userName = document.getElementById('user_name').value.trim();

    if (!userName) {
        alert('请输入用户名');
        return;
    }
    if (!username) {
        alert('请输入账号');
        return;
    }
    if (username.length < 5) {
        alert('账号至少需要5个字符');
        return;
    }
    if (!password) {
        alert('请输入密码');
        return;
    }
    if (password.length < 5) {
        alert('密码至少需要5个字符');
        return;
    }
    if (/^\d+$/.test(password)) {
        alert('密码不能是纯数字');
        return;
    }
    if (!confirmPassword) {
        alert('请再次输入密码');
        return;
    }
    if (password !== confirmPassword) {
        alert('两次输入的密码不一致');
        return;
    }

    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&userName=${encodeURIComponent(userName)}`
    })
        .then(response => response.json())
        .then(result => {
            if (result.status === 'success') {
                alert('注册成功！即将跳转登录页...');
                window.location.href = 'login.html';
            } else {
                alert(result.message || '注册失败，请重试');
            }
        })
        .catch(error => {
            console.error('注册失败:', error);
            alert('注册过程中发生错误，请稍后再试。');
        });

    // // 发送请求
    // fetch('/register', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/x-www-form-urlencoded',
    //     },
    //     body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
    // })
    //     .then(response => response.text())
    //     .then(result => {
    //         if (result === 'success') {
    //             alert('注册成功！即将跳转登录页...');
    //             window.location.href = 'login.html';
    //         } else {
    //             alert(result);
    //         }
    //     })
    //     .catch(error => {
    //         console.error('注册失败:', error);
    //         alert('注册过程中发生错误，请稍后再试。');
    //     });
}
document.addEventListener('DOMContentLoaded', function () {//拖动效果
    const draggableBox = document.getElementById('draggableBox');
    if (!draggableBox) return;

    let isDragging = false;
    let offsetX, offsetY;

    draggableBox.addEventListener('mousedown', (e) => {
        isDragging = true;
        offsetX = e.clientX - draggableBox.offsetLeft;
        offsetY = e.clientY - draggableBox.offsetTop;
        draggableBox.style.cursor = 'grabbing';
    });

    document.addEventListener('mousemove', (e) => {
        if (!isDragging) return;
        e.preventDefault();
        draggableBox.style.left = `${e.clientX - offsetX}px`;
        draggableBox.style.top = `${e.clientY - offsetY}px`;
    });

    document.addEventListener('mouseup', () => {
        isDragging = false;
        draggableBox.style.cursor = 'grab';
    });
});


document.addEventListener('DOMContentLoaded', function () {//登录注册
    const userStatusContainer = document.getElementById('user-status');

    if (!userStatusContainer) return;

    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    const user_name = localStorage.getItem('user_name');

    if (isLoggedIn) {
        userStatusContainer.innerHTML = `
            <span style="color: #555; margin-right: 15px;">欢迎你，${user_name}</span>
            <a href="#" class="logout-btn register-btn" style="background-color: #ff5353;">注销</a>
        `;
    } else {
        userStatusContainer.innerHTML = `
            <a href="login.html" class="login-btn">登录</a>
            <a href="register.html" class="register-btn">注册</a>
        `;
    }

    const logoutBtn = document.querySelector('.logout-btn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function (e) {
            e.preventDefault();
            localStorage.removeItem('isLoggedIn');
            localStorage.removeItem('username');
            window.location.reload(); // 刷新页面
        });
    }
});

document.addEventListener('DOMContentLoaded', () => {
    const bubbles = document.querySelectorAll('.bubble');
    bubbles.forEach(bubble => {
        const randomColor = bubbleColors[Math.floor(Math.random() * bubbleColors.length)];
        bubble.style.backgroundColor = randomColor;
    });
});

    const sidebar = document.querySelector('.sidebar');
    const toggleBtn = document.getElementById('sidebar-toggle');

    toggleBtn.addEventListener('click', () => {
    sidebar.classList.toggle('collapsed');
    // 同时旋转图标方向
    const icon = toggleBtn.querySelector('i');
    icon.classList.toggle('fa-angle-right');
    icon.classList.toggle('fa-angle-left');
});

