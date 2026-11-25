import java.util.*;

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

class bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        ArrayList<SinhVien> ds = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] a = sc.nextLine().trim().split("\\s+");
            String maHe = a[0];
            String maSV = a[1];
            int tinChi = Integer.parseInt(a[2]);

            if (maHe.equals("CQ")) {
                ds.add(new ChinhQuy(maSV, tinChi));
            } else if (maHe.equals("CLC")) {
                ds.add(new ChatLuongCao(maSV, tinChi));
            } else if (maHe.equals("LT")) {
                ds.add(new LienThong(maSV, tinChi));
            }
        }

        for (SinhVien sv : ds) {
            System.out.println(sv.getMaSV() + " " + sv.maHe() + " " + sv.hocPhi());
        }
        sc.close();
    }
}
