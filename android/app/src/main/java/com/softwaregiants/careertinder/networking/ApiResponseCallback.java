package com.softwaregiants.careertinder.networking;

import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.LoginSuccessModel;

public interface ApiResponseCallback {

    public void onSuccess(BaseBean baseBean);

    public void onFailure(Throwable t);
}
