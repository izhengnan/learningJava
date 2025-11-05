const audio = document.getElementById('audio-player');
const currentSong = document.getElementById('current-song');
const playlist = document.getElementById('playlist');
const loopToggle = document.getElementById('loop-toggle');
const shuffleToggle = document.getElementById('shuffle-toggle');
const progressBar = document.getElementById('progress-bar');
const currentTimeEl = document.getElementById('current-time');
const totalTimeEl = document.getElementById('total-time');
const volumeSlider = document.getElementById('volume-slider');
const volumeLevel = document.getElementById('volume-level');
const albumArt = document.getElementById('album-art');


let songs = [
    {
        name: '云宫迅音',
        artist: '网络歌手',
        path: '/music/云宫迅音.mp3',
        cover: 'https://via.placeholder.com/200/ff9a9e/ffffff?text=云宫迅音'
    },
    {
        name: '起风了',
        artist: '网络歌手',
        path: '/music/起风了.mp3',
        cover: 'https://via.placeholder.com/200/fad0c4/ffffff?text=起风了'
    }
];
let isSidebarCollapsed = true;
let currentIndex = 0;
let isPlaying = false;
let isLoop = false;
let isShuffle = false;

// 初始化播放器
function initPlayer() {
    renderPlaylist();
    loadCurrentSong();
    setupEventListeners();
    audio.volume = 0.7;
    updateVolumeUI();
}

// 渲染播放列表
function renderPlaylist() {
    playlist.innerHTML = '';
    songs.forEach((song, index) => {
        const li = document.createElement('li');
        li.innerHTML = `
                <i class="fas fa-music song-icon"></i>
                <div>
                    <div class="song-title">${song.name}</div>
                    <div class="song-artist">${song.artist || '未知艺术家'}</div>
                </div>
            `;
        li.addEventListener('click', () => playSong(index));
        playlist.appendChild(li);
    });
    updateActiveItem();
}

function togglePlaylist() {
    const sidebar = document.getElementById('playlist-sidebar');
    isSidebarCollapsed = !isSidebarCollapsed;
    sidebar.classList.toggle('collapsed', isSidebarCollapsed);
}

// 设置事件监听器
function setupEventListeners() {
    // 进度条点击事件
    document.querySelector('.progress-container').addEventListener('click', (e) => {
        const rect = e.target.getBoundingClientRect();
        const pos = (e.clientX - rect.left) / rect.width;
        audio.currentTime = pos * audio.duration;
    });

    // 音量控制
    volumeSlider.addEventListener('click', (e) => {
        const rect = volumeSlider.getBoundingClientRect();
        const pos = (e.clientX - rect.left) / rect.width;
        audio.volume = pos;
        updateVolumeUI();
    });

    // 时间更新
    audio.addEventListener('timeupdate', () => {
        const percent = (audio.currentTime / audio.duration) * 100;
        progressBar.style.width = percent + '%';

        // 更新时间显示
        currentTimeEl.textContent = formatTime(audio.currentTime);
        if (!isNaN(audio.duration)) {
            totalTimeEl.textContent = formatTime(audio.duration);
        }
    });

    // 歌曲结束
    audio.addEventListener('ended', () => {
        if (isLoop) {
            audio.currentTime = 0;
            audio.play();
        } else {
            nextSong();
        }
    });

    // 元数据加载
    audio.addEventListener('loadedmetadata', () => {
        if (!isNaN(audio.duration)) {
            totalTimeEl.textContent = formatTime(audio.duration);
        }
    });

    // 循环切换
    loopToggle.addEventListener('change', () => {
        isLoop = loopToggle.checked;
    });

    // 随机播放切换
    shuffleToggle.addEventListener('change', () => {
        isShuffle = shuffleToggle.checked;
    });

    // 上传本地音乐
    document.getElementById('upload-music').addEventListener('change', function (e) {
        const files = e.target.files;
        for (let file of files) {
            if (file.type.startsWith('audio/')) {
                const url = URL.createObjectURL(file);
                songs.push({
                    name: file.name.replace(/\.[^/.]+$/, ""),
                    artist: '本地音乐',
                    path: url,
                    cover: 'https://via.placeholder.com/200/cccccc/ffffff?text=Local'
                });
            }
        }
        renderPlaylist();
    });
}

// 加载当前歌曲
function loadCurrentSong() {
    if (songs.length === 0) return;
    const song = songs[currentIndex];
    audio.src = song.path;
    currentSong.textContent = `${song.name} - ${song.artist || '未知艺术家'}`;

    if (song.cover) {
        albumArt.style.backgroundImage = `url('${song.cover}')`;
    } else {
        albumArt.style.backgroundImage = 'url("https://via.placeholder.com/200/cccccc/ffffff?text=Music")';
    }


    const disc = document.querySelector('.disc-container');
    if (isPlaying) {
        disc.classList.add('playing');
    } else {
        disc.classList.remove('playing');
    }

    updateActiveItem();
}

function updateActiveItem() {
    Array.from(playlist.children).forEach((li, idx) => {
        li.classList.toggle('active', idx === currentIndex);
    });
}

// 播放/暂停切换
function togglePlayPause() {
    if (!songs.length) return;
    const playPauseBtn = document.getElementById('play-pause');
    const disc = document.querySelector('.disc-container');

    if (isPlaying) {
        audio.pause();
        playPauseBtn.innerHTML = '<i class="fas fa-play"></i>';
        disc.classList.remove('playing');
    } else {
        audio.play();
        playPauseBtn.innerHTML = '<i class="fas fa-pause"></i>';
        disc.classList.add('playing');
    }
    isPlaying = !isPlaying;
}

// 上一首
function prevSong() {
    if (songs.length === 0) return;
    let nextIndex = isShuffle ?
        Math.floor(Math.random() * songs.length) :
        (currentIndex - 1 + songs.length) % songs.length;
    playSong(nextIndex);
}

// 下一首
function nextSong() {
    if (songs.length === 0) return;
    let nextIndex = isShuffle ?
        Math.floor(Math.random() * songs.length) :
        (currentIndex + 1) % songs.length;
    playSong(nextIndex);
}

// 播放指定歌曲
function playSong(index) {
    if (songs.length === 0) return;
    currentIndex = index;
    loadCurrentSong();
    audio.play();
    isPlaying = true;
    document.getElementById('play-pause').innerHTML = '<i class="fas fa-pause"></i>';
    document.querySelector('.disc-container').classList.add('playing');
}

//
// function toggleShuffle() {
//     isShuffle = !isShuffle;
//     shuffleToggle.checked = isShuffle;
// }
//
//
// function toggleLoop() {
//     isLoop = !isLoop;
//     loopToggle.checked = isLoop;
// }
// let playMode = 'normal'; // 可选值：'normal', 'shuffle', 'loop'

// 定义播放模式枚举
const PlayMode = {
    LOOP: 'loop',
    SHUFFLE: 'shuffle'
};

// 初始化为循环播放
let playMode = PlayMode.LOOP;

// 切换播放模式
function togglePlayMode() {
    const modeBtn = document.querySelector('.control-btn:nth-child(1)'); // 获取第一个控制按钮

    if (playMode === PlayMode.LOOP) {
        // 切换到随机播放
        playMode = PlayMode.SHUFFLE;
        modeBtn.innerHTML = '<i class="fas fa-random"></i>';
        isShuffle = true;
        isLoop = false;
        shuffleToggle.checked = true;
        loopToggle.checked = false;
    } else {
        // 切换回循环播放
        playMode = PlayMode.LOOP;
        modeBtn.innerHTML = '<i class="fas fa-redo"></i>';
        isShuffle = false;
        isLoop = true;
        shuffleToggle.checked = false;
        loopToggle.checked = true;
    }

    // updatePlayModeUI();
}

// 更新 UI 样式
function updatePlayModeUI() {
    // const modeBtn = document.querySelector('.control-btn:nth-child(1)');
    // modeBtn.classList.toggle('active-mode', playMode !== PlayMode.LOOP);
}
// 设置默认为循环播放
isLoop = true;
loopToggle.checked = true;
playMode = PlayMode.LOOP;
document.querySelector('.control-btn:nth-child(1)').innerHTML = '<i class="fas fa-redo"></i>';

// 更新音量UI
function updateVolumeUI() {
    volumeLevel.style.width = (audio.volume * 100) + '%';
}

// 格式化时间
function formatTime(seconds) {
    const min = Math.floor(seconds / 60);
    const sec = Math.floor(seconds % 60);
    return `${min}:${sec.toString().padStart(2, '0')}`;
}

// 初始化
window.onload = initPlayer;