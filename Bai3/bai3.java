import java.util.*;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

abstract class SinhVien {
    protected String maSV;
    protected int soTinChi;

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();
    public abstract String maHe();

    public String getMaSV() {
        return maSV;
    }
}

class ChinhQuy extends SinhVien {
    public ChinhQuy(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) soTinChi * 450000;
    }

    @Override
    public String maHe() {
        return "CQ";
    }
}

class ChatLuongCao extends SinhVien {
    public ChatLuongCao(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) soTinChi * 450000 * 3 / 2;
    }

    @Override
    public String maHe() {
        return "CLC";
    }
}

class LienThong extends SinhVien {
    public LienThong(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) soTinChi * 450000 + 2000000;
    }

    @Override
    public String maHe() {
        return "LT";
    }
}

class QuanLyHocPhi {
    private ArrayList<SinhVien> ds = new ArrayList<>();
    private HashSet<String> maDaCo = new HashSet<>();

    public void themSinhVien(String maHe, String maSV, String chuoiTinChi)
            throws DuLieuKhongHopLeException {

        if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
            throw new DuLieuKhongHopLeException("Ma he khong hop le");
        }

        int tinChi;
        try {
            tinChi = Integer.parseInt(chuoiTinChi);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("So tin chi khong phai so nguyen");
        }

        if (tinChi < 1 || tinChi > 30) {
            throw new DuLieuKhongHopLeException("So tin chi ngoai pham vi");
        }

        if (maDaCo.contains(maSV)) {
            throw new DuLieuKhongHopLeException("Ma sinh vien bi trung");
        }

        SinhVien sv;
        if (maHe.equals("CQ")) {
            sv = new ChinhQuy(maSV, tinChi);
        } else if (maHe.equals("CLC")) {
            sv = new ChatLuongCao(maSV, tinChi);
        } else {
            sv = new LienThong(maSV, tinChi);
        }

        ds.add(sv);
        maDaCo.add(maSV);
    }

    public void hienThiKetQua() {
        ds.sort((a, b) -> {
            int soSanhHocPhi = Long.compare(b.hocPhi(), a.hocPhi());
            if (soSanhHocPhi != 0) {
                return soSanhHocPhi;
            }
            return a.getMaSV().compareTo(b.getMaSV());
        });

        long tong = 0;
        for (SinhVien sv : ds) {
            System.out.println(sv.getMaSV() + " " + sv.maHe() + " " + sv.hocPhi());
            tong += sv.hocPhi();
        }
        System.out.println("TONG " + tong);
    }
}

class bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        QuanLyHocPhi ql = new QuanLyHocPhi();

        for (int i = 0; i < n; i++) {
            String[] a = sc.nextLine().trim().split("\\s+");
            String maHe = a[0];
            String maSV = a[1];
            String tinChi = a[2];

            try {
                ql.themSinhVien(maHe, maSV, tinChi);
            } catch (DuLieuKhongHopLeException e) {
                System.out.println("INVALID " + maSV);
            }
        }

        ql.hienThiKetQua();
        sc.close();
    }
}
