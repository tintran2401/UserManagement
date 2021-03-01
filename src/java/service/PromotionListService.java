/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PromotionListDAO;
import dao.UserDAO;
import entities.TblPromotionList;
import entities.TblUser;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author TiTi
 */
public class PromotionListService {

    public static final int DEFAULT_RANK = 5;
    public static final String ADD = "ADD";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";

    public TblPromotionList getCurrentPromotionByUsername(String username) {
        UserDAO userDAO = new UserDAO();
        TblUser user = userDAO.getUserByUsername(username);

        PromotionListDAO promotionListDAO = new PromotionListDAO();
        TblPromotionList promotionList = promotionListDAO.getPromotionListByUser(user);

        return promotionList;
    }

    public List<TblPromotionList> getAllCurrentPromotionList() {
        PromotionListDAO promotionListDAO = new PromotionListDAO();
        List<TblPromotionList> promotions = promotionListDAO.getAllCurrentPromotions();

        List<TblPromotionList> removedPromotions = new ArrayList<>();
        for (TblPromotionList promotion : promotions) {
            if (DELETE.equals(promotion.getAction())) {
                removedPromotions.add(promotion);
            }
        }
        promotions.removeAll(removedPromotions);

        return promotions;
    }

    public List<TblPromotionList> getPromotionHistory(TblUser user) {
        PromotionListDAO promotionListDAO = new PromotionListDAO();
        List<TblPromotionList> promotions = promotionListDAO.getAllCurrentPromotions(user);

        return promotions;
    }

    public boolean hasPromoted(String username) {
        TblPromotionList promotionList = this.getCurrentPromotionByUsername(username);
        if (promotionList == null) {
            return false;
        }
        return !DELETE.equals(promotionList.getAction());
    }

    public TblPromotionList insertUser(String username) {
        UserDAO userDAO = new UserDAO();
        TblUser user = userDAO.getUserByUsername(username);

        TblPromotionList promotionList = new TblPromotionList();
        promotionList.setUsername(user);
        promotionList.setCreatedDate(Date.from(Instant.now()));
        promotionList.setRank(DEFAULT_RANK);
        promotionList.setAction(ADD);

        PromotionListDAO promotionListDAO = new PromotionListDAO();
        return promotionListDAO.createPromotion(promotionList);
    }

    public TblPromotionList updatePromotion(int id, int rank) {
        // create new promotion based on this #id promotion
        PromotionListDAO promotionListDAO = new PromotionListDAO();
        TblPromotionList promotionList = promotionListDAO.getPromotionListById(id);

        TblPromotionList newPromotionList = new TblPromotionList();
        newPromotionList.setUsername(promotionList.getUsername());
        newPromotionList.setRank(rank);
        newPromotionList.setCreatedDate(Date.from(Instant.now()));
        newPromotionList.setAction(UPDATE);

        return promotionListDAO.createPromotion(newPromotionList);
    }

    public TblPromotionList deletePromotion(int id) {
        // create new promotion based on this #id promotion
        PromotionListDAO promotionListDAO = new PromotionListDAO();
        TblPromotionList promotionList = promotionListDAO.getPromotionListById(id);

        TblPromotionList newPromotionList = new TblPromotionList();
        newPromotionList.setUsername(promotionList.getUsername());
        newPromotionList.setRank(promotionList.getRank());
        newPromotionList.setCreatedDate(Date.from(Instant.now()));
        newPromotionList.setAction(DELETE);

        return promotionListDAO.createPromotion(newPromotionList);
    }
}
