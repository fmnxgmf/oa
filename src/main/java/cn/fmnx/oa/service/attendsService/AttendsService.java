package cn.fmnx.oa.service.attendsService;

public interface AttendsService {
    Integer findSignInCount(Long attendsUserId, String ymdDate);
}
