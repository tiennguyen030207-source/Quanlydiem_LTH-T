import java.util.*;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 50 / 100;
    }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 70 / 100;
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3000000);
    }
}

abstract class SinhVien {
    protected String maSV;
    protected int soTinChi;
    protected MienGiam mienGiam;

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
        this.mienGiam = null;
    }

    public abstract long hocPhi();
    public abstract String maHe();

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public long tienGiam() {
        return (mienGiam == null) ? 0 : mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - tienGiam();
    }

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

class bai4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        ArrayList<SinhVien> ds = new ArrayList<>();
        HashMap<String, SinhVien> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] a = sc.nextLine().trim().split("\\s+");
            String maHe = a[0];
            String maSV = a[1];
            int tinChi = Integer.parseInt(a[2]);

            SinhVien sv;
            if (maHe.equals("CQ")) {
                sv = new ChinhQuy(maSV, tinChi);
            } else if (maHe.equals("CLC")) {
                sv = new ChatLuongCao(maSV, tinChi);
            } else {
                sv = new LienThong(maSV, tinChi);
            }

            ds.add(sv);
            map.put(maSV, sv);
        }

        int m = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < m; i++) {
            String[] a = sc.nextLine().trim().split("\\s+");
            String maSV = a[0];
            String maCS = a[1];

            SinhVien sv = map.get(maSV);
            if (sv == null) {
                System.out.println("NOT FOUND " + maSV);
                continue;
            }

            if (maCS.equals("CS")) {
                sv.setMienGiam(new ConChinhSach());
            } else if (maCS.equals("HN")) {
                sv.setMienGiam(new HoNgheo());
            } else if (maCS.equals("HB")) {
                sv.setMienGiam(new HocBong());
            }
        }

        for (SinhVien sv : ds) {
            System.out.println(sv.getMaSV() + " " + sv.maHe() + " "
                    + sv.hocPhi() + " " + sv.tienGiam() + " " + sv.phaiNop());
        }

        String[] cacHe = {"CQ", "CLC", "LT"};
        for (String he : cacHe) {
            int soLuong = 0;
            long tongPhaiNop = 0;

            for (SinhVien sv : ds) {
                if (sv.maHe().equals(he)) {
                    soLuong++;
                    tongPhaiNop += sv.phaiNop();
                }
            }

            if (soLuong > 0) {
                System.out.println(he + " " + soLuong + " " + tongPhaiNop);
            }
        }

        sc.close();
    }
}
