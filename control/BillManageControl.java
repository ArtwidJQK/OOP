package BillManagement.control;

import BillManagement.entity.HoaDon;
import BillManagement.entity.HoaDonNuocNgoai;
import BillManagement.persistence.FileDAO;
import java.util.ArrayList;

public class BillManagerControl {
    
    private DAO dao;
    private ArrayList<HoaDon> billList;

    public BillManagerControl() {
        this.dao = new FileDAO();
        this.billList = dao.loadData();
    }

    public void addBill(HoaDon hd) {
        billList.add(hd);
        dao.saveData(billList);
    }

    public void deleteBill(String maKH) {
        HoaDon toRemove = null;
        for (HoaDon hd : billList) {
            if (hd.getMaKH().equals(maKH)) {
                toRemove = hd;
                break;
            }
        }
        if (toRemove != null) {
            billList.remove(toRemove);
            dao.saveData(billList);
        }
    }

    public void updateBill(HoaDon updatedHd) {
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getMaKH().equals(updatedHd.getMaKH())) {
                billList.set(i, updatedHd);
                dao.saveData(billList);
                break;
            }
        }
    }

    public ArrayList<HoaDon> getBillList() {
        return this.billList;
    }
    
    public HoaDon getBillById(String maKH) {
        for (HoaDon hd : billList) {
            if (hd.getMaKH().equals(maKH)) {
                return hd;
            }
        }
        return null;
    }

    public double getAverageForeignerBill() {
        double total = 0;
        int count = 0;
        for (HoaDon hd : billList) {
            if (hd instanceof HoaDonNuocNgoai) {
                total += hd.tinhThanhTien();
                count++;
            }
        }
        return (count == 0) ? 0 : total / count;
    }
}
