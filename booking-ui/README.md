# Booking App UI

Đây là project giao diện Booking App được khởi tạo với Next.js và TypeScript.

## Cài đặt và chạy dự án

```bash
npm install
npm run dev
```

Sau đó mở trình duyệt và truy cập: [http://localhost:3000](http://localhost:3000)

## Cấu trúc thư mục chính

- `src/app/` — Chứa các trang chính của ứng dụng
- `src/components/` — Chứa các component dùng chung
- `src/i18n/locales/` — Chứa các file ngôn ngữ (i18n), ví dụ: en.json, vi.json. Có thể chia nhỏ theo module nếu dự án lớn.
- `src/constants/` — Chứa các hằng số dùng chung
- `src/services/` — Chứa các API service
- `src/redux/` — Chứa các slice, store, ...
- `src/models/` — Chứa các model TypeScript
- `src/lib/` — Chứa các thư viện tự viết, utils
- `src/assets/` — Chứa ảnh, icon, css, ...

**Best practice:**
- Tách biệt rõ ràng các phần: UI, logic, model, service, ...
- Đặt tên thư mục và file nhất quán, dễ hiểu.
- Có thể tạo thêm `components/admin/`, `components/guest/` nếu có component đặc thù cho từng vai trò.
- File ngôn ngữ nên chia nhỏ theo module nếu dự án lớn.
