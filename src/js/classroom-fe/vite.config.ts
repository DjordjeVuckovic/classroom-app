import {defineConfig, loadEnv} from 'vite'
import react from '@vitejs/plugin-react'
import replaceEnvValues from "./src/replace";


// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
      react()
  ],
    build : {
      rollupOptions : {
          plugins: [replaceEnvValues()]
      }
    }
})
