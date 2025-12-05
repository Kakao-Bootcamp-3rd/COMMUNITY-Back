
// 환경별로 API 서버 주소 관리
export const CONFIG = {
  API_BASE: "http://localhost:8080/api/v1",
  AUTH_BASE: "http://localhost:8080/api/v1/auth",
  USER_BASE: "http://localhost:8080/api/v1/users",
};

// 환경 감지로 자동 전환
export const ENV = {
  isDev: location.hostname === "localhost",
};
