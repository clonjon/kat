/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.idfor.kat.tools.UIBackend.api;

import com.idfor.kat.tools.UIBackend.dao.IamGroupRepository;
import com.idfor.kat.tools.UIBackend.dao.IamRoleRepository;
import com.idfor.kat.tools.UIBackend.model.iam.IamGroup;
import com.idfor.kat.tools.UIBackend.model.iam.IamRole;

import java.util.List;

public class IamGroupRestImpl implements IamGroupRest {
    private IamGroupRepository iamGroupRepository = new IamGroupRepository();

    public List<IamGroup> getAllIamGroups() {

        return this.iamGroupRepository.findAll();
    }

    @Override
    public IamGroup postGroup(IamGroup iamGroup) {
        return this.iamGroupRepository.save(iamGroup);
    }

    @Override
    public IamGroup putGroup(IamGroup iamGroup) {
        return this.iamGroupRepository.save(iamGroup);
    }

    @Override
    public void deleteGroup(String idGroup) {
        this.iamGroupRepository.delete(idGroup);
    }
}
