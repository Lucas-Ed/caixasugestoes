// package dev.sdras.caixasugestoes.config.exception;

// public class RegraDeNegocioException extends Exception{
//     public RegraDeNegocioException(String message) {
//         super(message);
//     }
// }
package dev.sdras.caixasugestoes.config.exception;

public class RegraDeNegocioException extends RuntimeException {
    public RegraDeNegocioException(String message) {
        super(message);
    }
}
