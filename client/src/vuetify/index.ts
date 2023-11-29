import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import * as styles from "vuetify/_styles.scss";

import colors from 'vuetify/lib/util/colors'

const vuetify = createVuetify( {
    theme: {
        defaultTheme: "dark",
    },

    components,
    directives,
    styles
});

export { vuetify }