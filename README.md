# 🎵 JSpringAmp (Alpha)  
*A personal browser-based music player that actually doesn't suck.*

JSpringAmp is a full-stack Java + Webamp powered music player you run for **yourself**, in **your browser**, from **your server** — because the world doesn’t need another half-baked, GTK-infested Linux desktop player vomiting segmentation faults every time you press "pause."

This is a **local-first**, single-user cloud music player. It plays music. It does it well. It doesn’t nag you to "go pro" or show you an ad for crypto every 10 seconds. Upload your tracks, then hit play. Simple.

---

## 🚀 Features (Alpha 0.1)
- Upload `.mp3` or `.ogg` files via [`/upload.html`](http://localhost:8080/upload.html)
- Tracks are saved to disk with metadata (title + artist)
- Playback via gorgeous retro Webamp interface on [`/`](http://localhost:8080)
- Persistent metadata saved in JSON
- Runs anywhere Spring Boot runs
- Classic pixel style with Java/Solaris-era vibes

---

## 💾 Requirements

- Java 17+
- Maven

---

## 📦 How to Use

1. Clone and build:

```bash
   git clone https://github.com/MARKMENTAL/jspringamp.git
   cd jspringamp
   mvn spring-boot:run
```

2. Open [`http://localhost:8080/upload.html`](http://localhost:8080/upload.html) to upload your music.
3. Go to [`http://localhost:8080`](http://localhost:8080) to listen.

---

## ☁️ Self-Hosting / Cloud Usage

Yes, it works over LAN. Yes, you can expose it to the internet and pretend you're your own music service.

Just remember: **this is meant for one user** — *you*.
If you try turning this into a streaming service or SaaS abomination, you're on your own.
No auth system, no permissions, no social, no begging for donations.

---

## 💥 No, I Won’t Add:

* Multi-user support
* Discord integration
* Spotify linking
* OAuth anything
* “Can you make a React frontend instead?” — **No.**

Fork it and ruin it yourself if that’s what you want. This is a **personal music player**. I built it for **myself first**, not as a product.

---

## 🧠 Why?

Because I was sick of bloated music players that:

* Are electron nightmares
* Don't play local files well
* Require 13 steps to upload music
* Have dependencies longer than my Spotify Wrapped

JSpringAmp is minimal, retro, and actually kind of fun.

---

## ⚖️ License

GPL v3 — because if you’re going to steal this and make it proprietary, I want to make your life legally complicated.

---

## 📡 Live Demo?

Nope. You run it. You break it. You enjoy it.

---

## 🧙‍♂️ Nerd Notes

* Metadata is stored in `uploads/tracklist.json`
* Audio files go in `uploads/`
* Backend is raw Java Spring Boot — no ORM, no database needed
* Frontend uses Webamp, Butterchurn, and vanilla JS

---

## 🛠️ TODO / Ideas (if I ever care enough):

* CLI tag editor?

---

## 🏗️ Build & CI

Every push to `main` automatically triggers a GitHub Actions workflow that:

- Builds JSpringAmp using Maven
- Generates a `JSpringAmp-0.0.1-SNAPSHOT.jar` file
- Uploads the `.jar` as an artifact for download on the [Actions tab](https://github.com/MARKMENTAL/jspringamp/actions)

You can grab the latest successful build without even cloning the repo if you’re lazy or allergic to terminals.

---

## 🧪 Current Version

`0.0.1-SNAPSHOT` — it runs, it slaps, it still might throw a stacktrace at you if you sneeze wrong.

---

