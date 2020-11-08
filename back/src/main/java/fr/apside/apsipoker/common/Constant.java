package fr.apside.apsipoker.common;

public final class Constant {
    public static final String REST_URL = "/rest/v1";

    /* **********************************
     *               ERRORS              *
     ** **********************************/
    public static final class Errors {
        public static final String AUTHENTICATION_ERROR = "AUTHENTICATION_ERROR";

        public static final class TOURNAMENT {
            private static final String PREFIX = "TOURNAMENT.";

            public static final String DATE_EMPTY = PREFIX + "DATE_EMPTY";
        }

        public static final class USER {
            private static final String PREFIX = "USER.";

            public static final String NOT_FOUND = PREFIX + "NOT_FOUND";
            public static final String NAME_EMPTY = PREFIX + "NAME_EMPTY";
            public static final String ROLE_EMPTY = PREFIX + "ROLE_EMPTY";
            public static final String AGENCY_EMPTY = PREFIX + "AGENCY_EMPTY";
            public static final String PASSWORD_EMPTY = PREFIX + "PASSWORD_EMPTY";
        }
    }
}
