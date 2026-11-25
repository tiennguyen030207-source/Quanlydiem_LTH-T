import java.util.*;

class SinhVien {
    private String maSV;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String maSV, String hoTen, String ngaySinh) {
        this.maSV = maSV;
        this.hoTen = chuanHoaTen(hoTen);
        this.ngaySinh = chuanHoaNgay(ngaySinh);
    }

    private String chuanHoaTen(String ten) {
        String[] cacTu = ten.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String tu : cacTu) {
            if (!tu.isEmpty()) {
                sb.append(Character.toUpperCase(tu.charAt(0)));
                sb.append(tu.substring(1));
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    private String chuanHoaNgay(String ngay) {
        String[] parts = ngay.trim().split("/");
        int d = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        return String.format("%02d/%02d/%04d", d, m, y);
    }

    public void hienThi() {
        System.out.println(maSV + " " + hoTen + " " + ngaySinh);
    }
}

class bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        ArrayList<SinhVien> ds = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine().trim();
            String ten = sc.nextLine();
            String ngay = sc.nextLine().trim();
            ds.add(new SinhVien(ma, ten, ngay));
        }

        for (SinhVien sv : ds) {
            sv.hienThi();
        }
        sc.close();
    }
}
