import axios from "axios";

const server = process.env.NODE_ENV || "http://localhost:8080";

const getJwt = () => localStorage.getItem("jwt") || "";

const getAuthHeaders = () => ({
  Authorization: `Bearer ${getJwt()}`,
});

// Các hàm gọi API
export const post = (uri: string, data: any) =>
  axios.post(server + uri, data, {
    headers: getAuthHeaders(),
    withCredentials: true,
  });

export const put = (uri: string, data: any) =>
  axios.put(server + uri, data, {
    headers: getAuthHeaders(),
    withCredentials: true,
  });

export const get = (uri: string) =>
  axios.get(server + uri, {
    headers: getAuthHeaders(),
    withCredentials: true,
  });

export const deleteRequest = (uri: string) =>
  axios.delete(server + uri, {
    headers: getAuthHeaders(),
    withCredentials: true,
  });

// Nếu cần gọi mà không có header
export const postWithHeader = (uri: string, data: any) =>
  axios.post(server + uri, data, {
    withCredentials: true,
  });

export const getWithHeader = (uri: string) =>
  axios.get(server + uri, {
    withCredentials: true,
  });
