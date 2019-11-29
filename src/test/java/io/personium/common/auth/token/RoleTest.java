/**
 * personium.io
 * Copyright 2014 FUJITSU LIMITED
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.personium.common.auth.token;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;

import org.junit.Test;

/**
 * トークン処理ライブラリのユニットテストクラス.
 */
public class RoleTest {

    /**
     * test for createFromRoleClassUrl normal usage.
     * @throws MalformedURLException
     */
    @Test
    public void createFromRoleClassUrl_normal() throws MalformedURLException {
        String baseUrl = "https://localhost:8080/personium-core/testcell1/";
        String roleUrl = "__role/__/role1";
        Role role = Role.createFromRoleClassUrl(baseUrl + roleUrl);
        assertNotNull(role);
        assertEquals(baseUrl, role.getBaseUrl());
        assertEquals(baseUrl + roleUrl, role.toRoleClassURL());
    }

    /**
     * createFromRoleClassUrl when nonRoleUrl given should throw MalformedURLException.
     * @throws MalformedURLException
     */
    @Test(expected = MalformedURLException.class)
    public void createFromRoleClassUrl_nonRoleUrl() throws MalformedURLException {
        String baseUrl = "https://localhost:8080/personium-core/testcell1/";
        String roleUrl = "";
        Role.createFromRoleClassUrl(baseUrl + roleUrl);
    }

    /**
     * createFromRoleClassUrl when given RoleInstanceUrl should throw MalformedURLException.
     * @throws MalformedURLException
     */
    @Test(expected = MalformedURLException.class)
    public void createFromRoleClassUrl_RoleInstanceUrl() throws MalformedURLException {
        String baseUrl = "https://localhost:8080/personium-core/testcell1/";
        String roleUrl = "__role/bx/hoge";
        Role.createFromRoleClassUrl(baseUrl + roleUrl);
    }

    /**
     * Roleのコンストラクタのテスト(URLにBox名までしかない).
     * @throws MalformedURLException URLパースエラー
     */
    @Test(expected = MalformedURLException.class)
    public void testRoleConstructWithBox() throws MalformedURLException {
        String baseUrl = "https://localhost:8080/personium-core/testcell1/";
        String roleUrl = "__role/__";
        Role.createFromRoleClassUrl(baseUrl + roleUrl);
    }

    /**
     * Roleのコンストラクタのテスト(URLがURL形式ではない).
     * @throws MalformedURLException URLパースエラー
     */
    @Test(expected = MalformedURLException.class)
    public void testRoleConstructWithBadURL() throws MalformedURLException {
        String baseUrl = "BadURL";
        Role.createFromRoleInstanceUrl(baseUrl);
    }

}
