// auth.js
import { CONFIG } from "../common/config.js";


const API_BASE = CONFIG.API_BASE;

document.addEventListener("DOMContentLoaded", () => {
  const signupForm = document.getElementById("signup-form");
  const loginForm = document.getElementById("login-form");

  if (signupForm) {
    signupForm.addEventListener("submit", async (e) => {
      e.preventDefault();

      const data = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
      };

      try {
        const res = await fetch(`${API_BASE}/signup`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data),
        });
        const json = await res.json();

        if (res.ok) {
          alert("회원가입이 완료되었습니다!");
          window.location.href = "./login.html";
        } else {
          alert(json.message || "회원가입 실패");
        }
      } catch (err) {
        console.error(err);
        alert("서버 연결 오류");
      }
    });
  }
  if (loginForm) {
    loginForm.addEventListener("submit", async (e) => {
      e.preventDefault();

      const data = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
      };

      try {
        const res = await fetch(`${API_BASE}/login`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data),
        });
        const json = await res.json();

        if (res.ok) {
          localStorage.setItem("accessToken", json.data.accessToken);
          alert("로그인 성공!");
          window.location.href = "../post/list.html";
        } else {
          alert(json.message || "로그인 실패");
        }
      } catch (err) {
        console.error(err);
        alert("서버 연결 오류");
      }
    });
  }
});
